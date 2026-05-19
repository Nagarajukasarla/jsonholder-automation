package com.nagaraju.config;

public enum Environment {
    DEV,
    QA,
    UAT,
    PROD;

    public static Environment from(String value) {
        try {
            return Environment.valueOf(value.toUpperCase());
        }
        catch (Exception e) {
            System.out.println("Invalid environment: "
                            + value
                            + " defaulting to DEV");
            return DEV;
        }
    }
}
