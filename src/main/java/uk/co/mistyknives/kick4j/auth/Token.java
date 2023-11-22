package uk.co.mistyknives.kick4j.auth;

import co.casterlabs.rakurai.json.Rson;
import co.casterlabs.rakurai.json.element.JsonObject;

import de.taimos.totp.TOTP;

import lombok.SneakyThrows;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import uk.co.mistyknives.kick4j.exceptions.auth.TwoFAException;
import uk.co.mistyknives.kick4j.util.HttpRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Project: Kick4J
 * <br>
 * Copyright MistyKnives © 2022-2023
 * <br>
 * ---------------------------------------
 * <br>
 * All Projects are located on my GitHub
 * <br>
 * Please provide credit where due :)
 * <br>
 * ---------------------------------------
 * <br>
 * https://github.com/MistyKnives
 */
public class Token {

    private final AuthCredential credential;

    private final HttpRequest self = new HttpRequest();

    private String token;

    public Token(AuthCredential credential) {
        this.credential = credential;
    }

    /**
     * Generate the Client's token to use future authenticated routes with no troubles.
     * @return String
     * @throws IOException Any failed or interrupted exceptions returned from parsing JSON, etc.
     * @throws TwoFAException If 2FA Fails.
     */
    public String generate() throws IOException, TwoFAException {
        JsonObject tokenProvider = Rson.DEFAULT.fromJson(this.self.requestEmpty(new Request.Builder()
                .url("https://kick.com/kick-token-provider").header("Accept", "application/json")), JsonObject.class);

        JsonObject loginPayload = new JsonObject()
                .put("isMobileRequest", true)
                .put("email", this.credential.username())
                .put("password", this.credential.password())
                .put(tokenProvider.getString("nameFieldName"), "")
                .put(tokenProvider.getString("validFromFieldName"), tokenProvider.get("encryptedValidFrom"));

        if (this.credential.oneTimePassword() != null) {
            // User provides the generic 2FA code that is provided when adding 2FA to their account
            // e.g. 2FUJE243HIAAOWDI
            if(this.credential.oneTimePassword().length() > 6) {
                String totpCode = TOTP.getOTP(Hex.encodeHexString(new Base32().decode(this.credential.oneTimePassword())));
                loginPayload.put("one_time_password", totpCode);
            } else {
                // User puts 2FA Code from emails
                // e.g. 124523
                loginPayload.put("one_time_password", this.credential.oneTimePassword());
            }
        }

        try (Response response = HttpRequest.okHttpClient.newCall(new Request.Builder()
                                .url("https://kick.com/mobile/login")
                                .post(RequestBody.create(loginPayload.toString().getBytes(StandardCharsets.UTF_8), MediaType.parse("application/json")))
                                .header("Accept", "application/json")
                                .build()
                ).execute()) {
            JsonObject body = Rson.DEFAULT.fromJson(response.body().string(), JsonObject.class);

            if (body.containsKey("2fa_required") && body.getBoolean("2fa_required")) {
                throw new TwoFAException("2FA");
            }

            if (body.containsKey("message")) {
                throw new IOException(body.getString("message"));
            }

            return this.token = body.getString("token");
        }
    }

    /**
     * Regenerate the Client's token.
     * @return String
     */
    @SneakyThrows
    public String regenerate() {
        return this.token = this.generate();
    }

    /**
     * Get the Client's token.
     * @return String
     */
    public String get() {
        return token;
    }
}
