package com.nagaraju.base;

import com.nagaraju.config.ConfigManager;
import com.nagaraju.config.JsonReader;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();
        log.info("Environment: {}", ConfigManager.getEnvironment());
        log.info("Base URL: {}", ConfigManager.getBaseUrl());

        JsonReader.load();
    }
}
