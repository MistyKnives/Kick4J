package uk.co.mistyknives.kick4j.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import uk.co.mistyknives.kick4j.Kick4J;
import uk.co.mistyknives.kick4j.exceptions.api.*;
import uk.co.mistyknives.kick4j.exceptions.api.impl.*;

import java.io.IOException;
import java.util.List;

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
@NoArgsConstructor
public class HttpRequest {

    public Kick4J client;

    public HttpRequest(Kick4J client) {
        this.client = client;
    }

    public static final OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor((chain) -> chain.proceed(chain.request().newBuilder().removeHeader("Accept-Encoding").build())).build();

    public final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T request(Request.Builder builder, Class<T> returnType) throws KickException {
        if(client != null && client.getToken() != null) builder.addHeader("Authorization", "Bearer %s".formatted(client.getToken().get()));

        try (Response response = okHttpClient.newCall(builder.build()).execute()) {
            String responseBody = response.body().string();
            int responseCode = response.code();

            if(responseBody.startsWith("<!DOCTYPE html>")) return null;

            switch(responseCode) {
                case 400 -> throw new KickException(new BadRequestException("400: Bad Request"), responseCode, responseBody);
                case 403 -> throw new KickException(new ForbiddenException("403: Forbidden"), responseCode, responseBody);
                case 404 -> throw new KickException(new NotFoundException("404: Not Found"), responseCode, responseBody);

                // Codes that Token is regenerated
                case 401 -> {
                    if(client != null && client.getToken() != null) {
                        client.getToken().regenerate();
                        return request(builder, returnType);
                    }

                    throw new KickException(new UnauthorizedException("401: Unauthorized"), responseCode, responseBody);
                }
                case 429, 522, 524 -> {
                    if(client != null && client.getToken() != null) {
                        client.getToken().regenerate();
                        return request(builder, returnType);
                    }

                    throw new KickException(new TooManyRequestsException("429: Too Many Requests"), responseCode, responseBody);
                }

            }

            if (responseBody.startsWith("[")){
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType listType = typeFactory.constructCollectionType(List.class, returnType);
                return objectMapper.readValue(responseBody, listType);
            }
            else if (returnType == String.class) {
                return (T) responseBody;
            } else if (builder.getHeaders$okhttp().get("X-TYPESENSE-API-KEY") != null) {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                JsonNode documentNode = jsonNode.get("hits").path(0).path("document");

                if (documentNode.isMissingNode())
                    return null;
                return objectMapper.treeToValue(documentNode, returnType);
            } else return objectMapper.readValue(responseBody, returnType);
        } catch (IOException e) {
            throw new RuntimeException("There was an error while processing your Kick Request: " + e.getMessage(), e);
        }
    }

    public String requestEmpty(@NonNull Request.Builder builder) {
        try (Response response = okHttpClient.newCall(builder.build()).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
