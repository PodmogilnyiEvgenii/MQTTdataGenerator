import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTCallBacks {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainApp.class);

    public static class CallBacks implements MqttCallback {
        @Override
        public void connectionLost(Throwable throwable) {
            logger.error("Connection lost");
        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) {
            logger.info("Get message: {}", mqttMessage.toString());
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            try {
                logger.debug("Delivery message complete: {}", iMqttDeliveryToken.getMessage());
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
