package uk.co.mistyknives.kick4j.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
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
public class HttpConnection {

    public static Object getRawResponse(String s, Class clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--enable-javascript", "--enable-cookies");
            options.setHeadless(true);

            WebDriver driver = new FirefoxDriver(options);
            driver.get(s);

            Document document = Jsoup.parse(driver.getPageSource());
            System.out.println(document.text());

            return mapper.readValue(document.text(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getRawResponseFromNode(String s, String node, Class clazz) {
        try {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--enable-javascript", "--enable-cookies");
            options.setHeadless(true);

            WebDriver driver = new FirefoxDriver(options);
            driver.get(s);

            Document document = Jsoup.parse(driver.getPageSource());
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            JsonNode jsonNode = mapper.readTree(document.text()).get(node);
            JsonParser parser = mapper.treeAsTokens(jsonNode);

            return mapper.readValue(parser, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}