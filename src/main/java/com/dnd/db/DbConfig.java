package com.dnd.db;

public record DbConfig(String url, String user, String password) {

    public DbConfig {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("db url must not be blank");
        }
    }
}
