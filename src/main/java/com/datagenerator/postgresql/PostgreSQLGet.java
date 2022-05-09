package com.datagenerator.postgresql;

import com.datagenerator.carwash.CarWash;

import java.sql.*;


public class PostgreSQLGet {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PostgreSQLGet.class);

    public static void getId() {
        try {
            PreparedStatement request = PostgreSQLApp.postgresqlDbConnection.prepareStatement("SELECT * FROM carwash");
            ResultSet resultSet = request.executeQuery();
            while (resultSet.next()) {
                CarWash.carwashIdMap.put(resultSet.getInt("carwash_id"), resultSet.getString("carwash_num"));
            }
        } catch (SQLException e) {
            logger.error("Get carwash id error: {}", e.toString());
        }
    }

    public static void getValue() {
        try {
            PreparedStatement request = PostgreSQLApp.postgresqlDbConnection.prepareStatement("SELECT * FROM status_cash ORDER BY data_time DESC LIMIT 20");
            ResultSet resultSet = request.executeQuery();
            while (resultSet.next()) {
                if (CarWash.cashIncomeMap.get(CarWash.carwashIdMap.get(resultSet.getInt("carwash_id")))==null){
                    CarWash.cashIncomeMap.put(CarWash.carwashIdMap.get(resultSet.getInt("carwash_id")),resultSet.getInt("cash_income"));
                    CarWash.bankIncomeMap.put(CarWash.carwashIdMap.get(resultSet.getInt("carwash_id")),resultSet.getInt("bank_income"));
                }
            }
        } catch (SQLException e) {
            logger.error("Get last value error: {}", e.toString());
        }
    }
}
