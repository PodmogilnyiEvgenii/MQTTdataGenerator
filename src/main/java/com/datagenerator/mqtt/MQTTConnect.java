package com.datagenerator.mqtt;

import com.datagenerator.main.Options;
import org.eclipse.paho.client.mqttv3.*;

public class MQTTConnect {
    public static /*IMqttClient*/ IMqttAsyncClient mqttClient = null;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MQTTConnect.class);

    public static void connect() /*throws MqttException*/ {
        try {
            mqttClient = new /*MqttClient*/MqttAsyncClient(Options.optionsMap.get("MQTT_SERVER") + ":" + Options.optionsMap.get("MQTT_PORT"), Options.optionsMap.get("MQTT_CLIENT_ID"));
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(5);
            //mqttConnectOptions.setKeepAliveInterval(30);
            mqttConnectOptions.setUserName(Options.optionsMap.get("MQTT_LOGIN"));
            mqttConnectOptions.setPassword(Options.optionsMap.get("MQTT_PASS").toCharArray());

            mqttClient.setCallback(new MQTTCallBacks.CallBacks());
            mqttClient.connect(mqttConnectOptions);

            do {  } while (!mqttClient.isConnected());

            mqttClient.subscribe(Options.optionsMap.get("MQTT_TOPIC1"), 0);
            mqttClient.subscribe(Options.optionsMap.get("MQTT_TOPIC2"), 0);
        } catch (MqttException e) {
            //e.printStackTrace();
            logger.error("MQTT connecting error: {}", e.toString());
        }
    }
}
