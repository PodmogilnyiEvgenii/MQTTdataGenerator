package com.datagenerator.main;
import com.datagenerator.mqtt.MQTTApp;
import com.datagenerator.postgresql.PostgreSQLApp;

public class MainApp {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainApp.class);
    public static void main(String[] args) {
        Options.getOptions();
        PostgreSQLApp.activate();
        MQTTApp.activate();
    }
}



