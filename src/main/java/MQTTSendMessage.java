import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.LinkedHashMap;

public class MQTTSendMessage {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainApp.class);
    public static void send(int i) {

        ObjectMapper objectMapper = new ObjectMapper();
        LinkedHashMap<String, String> carWashData = CarWash.getData(i);

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(carWashData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }
        MqttMessage mqttMsg = new MqttMessage(jsonString.getBytes());
        mqttMsg.setQos(0);
        mqttMsg.setRetained(true);
        try {
            MQTTConnect.mqttClient.publish(MQTTOptions.optionsMap.get("MQTT_TOPIC1"), mqttMsg);
        } catch (MqttException e) {
            //e.printStackTrace();
            logger.error("MQTT sending message error: {}", e.toString());
        }
        logger.debug("Send message: {}", mqttMsg.toString());
    }
}
