package com.dnd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionProvider {

    private final DbConfig config;

    public ConnectionProvider(DbConfig config) {
        this.config = config;
    }

    public Connection getConnection() throws SQLException {
        if (config.user() == null) {
            return DriverManager.getConnection(config.url());
        }
        return DriverManager.getConnection(config.url(), config.user(), config.password());
    }
}
