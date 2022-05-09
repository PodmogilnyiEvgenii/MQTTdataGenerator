package com.datagenerator.postgresql;

import com.datagenerator.main.Options;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgreSQLConnect {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLConnect.class);

    public static void connect() {
        try {
            PostgreSQLApp.postgresqlDbConnection = DriverManager.getConnection(Options.optionsMap.get("DB_URL"), Options.optionsMap.get("DB_USER"), Options.optionsMap.get("DB_PASS"));
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            logger.error("Connection PostgreSQL error:  {}", e.toString());
        }
    }
}
