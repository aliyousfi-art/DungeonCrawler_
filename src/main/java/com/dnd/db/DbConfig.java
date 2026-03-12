package com.dnd.db;

/**
 * Immutable database connection settings.
 *
 * @param url      the JDBC URL (must not be blank)
 * @param user     the database user, or null for no authentication
 * @param password the database password, or null
 */
public record DbConfig(String url, String user, String password) {

    public DbConfig {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("db url must not be blank");
        }
    }
}
