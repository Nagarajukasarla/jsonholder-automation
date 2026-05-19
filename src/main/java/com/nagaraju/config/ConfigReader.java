package com.nagaraju.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader(String filename) {
        properties = new Properties();

        try(InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new RuntimeException("Property file not found: " + filename);
            }
            properties.load(input);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed loading config file", e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

}
