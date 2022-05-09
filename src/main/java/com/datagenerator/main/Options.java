package com.datagenerator.main;
import com.datagenerator.mqtt.MQTTOptionsFile;
import java.util.LinkedHashMap;


public class Options {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Options.class);
    public static final String filePath = "src\\main\\resources\\MQTT.options.cfg";
    public static LinkedHashMap<String, String> optionsMap = new LinkedHashMap();
    static {
        optionsMap.put("MQTT_SERVER", "tcp://srv2.clusterfly.ru");
        optionsMap.put("MQTT_PORT", "9991");
        optionsMap.put("MQTT_CLIENT_ID", "GEN");
        optionsMap.put("MQTT_LOGIN", "user_e26b81e5");
        optionsMap.put("MQTT_PASS", "pass_bd2225c3");
        optionsMap.put("MQTT_TOPIC1", "user_e26b81e5/uptime");
        optionsMap.put("MQTT_TOPIC2", "user_e26b81e5/test");
        optionsMap.put("DB_URL", "jdbc:postgresql://localhost:5432/mqtt_db");
        optionsMap.put("DB_USER", "mqttuser");
        optionsMap.put("DB_PASS", "mqttuser");
    }

    public static void getOptions() {
        if (MQTTOptionsFile.optionsFileFound()) {
            logger.debug("MQTT options file found");
        } else {
            logger.debug("MQTT options file not found. Creating new.");
            MQTTOptionsFile.create();
        }
        MQTTOptionsFile.read();
    }
}
