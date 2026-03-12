package com.dnd.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

/**
 * Loads {@link DbConfig} from a classpath properties file, falling back to the
 * DB_URL / DB_USER / DB_PASSWORD environment variables.
 */
public final class DbConfigLoader {

    private final String resourcePath;

    /**
     * @param resourcePath the classpath location of the properties file
     */
    public DbConfigLoader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * Loads the configuration.
     *
     * @return the resolved database configuration
     * @throws IllegalStateException if the file cannot be read or db.url is missing
     */
    public DbConfig load() {
        Properties props = loadProperties();

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

    /**
     * Loads the properties from the classpath, falling back to common
     * filesystem locations so the app runs regardless of IDE/build settings.
     *
     * @return the loaded properties (possibly empty if no file was found)
     */
    private Properties loadProperties() {
        Properties props = new Properties();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (in != null) {
                props.load(in);
                return props;
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to load DB config: " + resourcePath, ex);
        }

        // Classpath miss (e.g. resources not copied to build output): try the filesystem.
        for (Path candidate : new Path[]{
                Path.of("src", "main", "resources").resolve(resourcePath),
                Path.of(resourcePath)
        }) {
            if (Files.isRegularFile(candidate)) {
                try (InputStream in = Files.newInputStream(candidate)) {
                    props.load(in);
                    return props;
                } catch (IOException ex) {
                    throw new IllegalStateException("Unable to load DB config: " + candidate, ex);
                }
            }
        }

        return props;
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
