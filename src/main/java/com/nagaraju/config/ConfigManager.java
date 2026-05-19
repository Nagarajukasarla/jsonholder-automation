package com.nagaraju.config;

public class ConfigManager {
    private static final ConfigReader configReader;

    static {
        String envValue = System.getProperty("env", "dev");

        Environment env = Environment.from(envValue);

        configReader = new ConfigReader(
                env.name().toLowerCase() + ".properties"
        );
    }

    public static String getBaseUrl() {
        return configReader.get("base.url");
    }

    public static int getConnectionTimeout() {
        return Integer.parseInt(configReader.get("connection.timeout"));
    }

    public static int getReadTimeout() {
        return Integer.parseInt(configReader.get("read.timeout"));
    }

    public static String getEnvironment() {
        return configReader.get("environment");
    }
}
