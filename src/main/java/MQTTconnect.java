import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTconnect {
    public static IMqttClient mqttClient;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(mainApp.class);

    public static void Connect() /*throws MqttException*/ {
        try {
            mqttClient = new MqttClient(MQTToptions.optionsMap.get("MQTT_SERVER") + ":" + MQTToptions.optionsMap.get("MQTT_PORT"), MQTToptions.optionsMap.get("MQTT_CLIENT_ID"));
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(true);
            mqttConnectOptions.setConnectionTimeout(5);
            mqttConnectOptions.setUserName(MQTToptions.optionsMap.get("MQTT_LOGIN"));
            mqttConnectOptions.setPassword(MQTToptions.optionsMap.get("MQTT_PASS").toCharArray());

            mqttClient.setCallback(new MQTTcallBacks.CallBacks());
            mqttClient.connect(mqttConnectOptions);
            mqttClient.subscribe(MQTToptions.optionsMap.get("MQTT_TOPIC1"));
            mqttClient.subscribe(MQTToptions.optionsMap.get("MQTT_TOPIC2"));
        } catch (MqttException e) {
            //e.printStackTrace();
            logger.error("MQTT connecting error: {}", e.toString());
        }
    }
}
