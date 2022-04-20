import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTConnect {
    public static IMqttClient mqttClient;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainApp.class);

    public static void connect() /*throws MqttException*/ {
        try {
            mqttClient = new MqttClient(MQTTOptions.optionsMap.get("MQTT_SERVER") + ":" + MQTTOptions.optionsMap.get("MQTT_PORT"), MQTTOptions.optionsMap.get("MQTT_CLIENT_ID"));
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(5);
            mqttConnectOptions.setUserName(MQTTOptions.optionsMap.get("MQTT_LOGIN"));
            mqttConnectOptions.setPassword(MQTTOptions.optionsMap.get("MQTT_PASS").toCharArray());

            mqttClient.setCallback(new MQTTCallBacks.CallBacks());
            mqttClient.connect(mqttConnectOptions);
            mqttClient.subscribe(MQTTOptions.optionsMap.get("MQTT_TOPIC1"));
            mqttClient.subscribe(MQTTOptions.optionsMap.get("MQTT_TOPIC2"));
        } catch (MqttException e) {
            //e.printStackTrace();
            logger.error("MQTT connecting error: {}", e.toString());
        }
    }
}
