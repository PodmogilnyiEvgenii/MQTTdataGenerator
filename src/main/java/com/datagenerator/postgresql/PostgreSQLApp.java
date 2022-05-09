package com.datagenerator.postgresql;

import com.datagenerator.carwash.CarWash;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


public class PostgreSQLApp {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLApp.class);
    public static Connection postgresqlDbConnection = null;

    public static void activate() {
        PostgreSQLConnect.connect();
        PostgreSQLGet.getId();
        PrintId();
        PostgreSQLGet.getValue();
        PrintLastData();
    }

    public static void PrintLastData() {
        logger.debug("========================");
        for (Map.Entry<String, Integer> entry : CarWash.cashIncomeMap.entrySet()) {
            logger.debug("{} = {} = {}",entry.getKey(),entry.getValue(),CarWash.bankIncomeMap.get(entry.getKey()));
        }
        logger.debug("========================");
    }

    public static void PrintId() {
        logger.debug("========================");
        for (Map.Entry< Integer,String> entry : CarWash.carwashIdMap.entrySet()) {
            logger.debug("{} = {}",entry.getKey(),entry.getValue());
        }
        logger.debug("========================");
    }
}



