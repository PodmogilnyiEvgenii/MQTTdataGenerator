import java.io.File;
import java.util.HashMap;

public class MQTToptions {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(mainApp.class);
    public static final String filePath = "src\\main\\resources\\MQTT.options.cfg";
    public static HashMap<String, String> optionsMap = new HashMap<>();
    static {
        optionsMap.put("MQTT_SERVER", "tcp://srv2.clusterfly.ru");
        optionsMap.put("MQTT_PORT", "9991");
        optionsMap.put("MQTT_CLIENT_ID", "ClientDB");
        optionsMap.put("MQTT_LOGIN", "user_e26b81e5");
        optionsMap.put("MQTT_PASS", "pass_3a57aa79");
        optionsMap.put("MQTT_TOPIC1", "user_e26b81e5/uptime");
        optionsMap.put("MQTT_TOPIC2", "user_e26b81e5/test");
    }

    public static void GetOptions() {
        boolean mqttOptionsFound = new File(MQTToptions.filePath).exists();

        if (mqttOptionsFound) {
            logger.debug("MQTT options file found");
        } else {
            logger.debug("MQTT options file not found. Creating new.");
            MQTToptionsFile.create();
        }
        MQTToptionsFile.read();
    }
}
