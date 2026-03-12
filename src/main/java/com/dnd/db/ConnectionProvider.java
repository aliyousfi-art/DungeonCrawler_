package com.dnd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Supplies JDBC {@link java.sql.Connection} instances from a {@link DbConfig}.
 */
public final class ConnectionProvider {

    private final DbConfig config;

    public ConnectionProvider(DbConfig config) {
        this.config = config;
    }

    /**
     * Opens a new database connection.
     *
     * @return a new JDBC connection
     * @throws SQLException if the connection cannot be established
     */
    public Connection getConnection() throws SQLException {
        if (config.user() == null) {
            return DriverManager.getConnection(config.url());
        }
        return DriverManager.getConnection(config.url(), config.user(), config.password());
    }
}
