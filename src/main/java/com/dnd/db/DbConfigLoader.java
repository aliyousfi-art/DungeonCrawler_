package com.dnd.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public final class DbConfigLoader {

    private final String resourcePath;

    public DbConfigLoader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public DbConfig load() {
        Properties props = new Properties();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to load DB config: " + resourcePath, ex);
        }

        String url = firstNonBlank(
                props.getProperty("db.url"),
                System.getenv("DB_URL")
        ).orElseThrow(() -> new IllegalStateException("Missing db.url (or DB_URL env var)"));

        String user = firstNonBlank(
                props.getProperty("db.user"),
                System.getenv("DB_USER")
        ).orElse(null);

        String password = firstNonBlank(
                props.getProperty("db.password"),
                System.getenv("DB_PASSWORD")
        ).orElse(null);

        return new DbConfig(url, user, password);
    }

    private static Optional<String> firstNonBlank(String... values) {
        for (String v : values) {
            if (v != null && !v.isBlank()) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }
}
