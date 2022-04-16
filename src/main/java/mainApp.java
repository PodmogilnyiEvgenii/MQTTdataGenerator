import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;

import java.util.concurrent.TimeUnit;
import java.util.*;

public class mainApp {
    private static final String MQTT_SERVER = "tcp://srv2.clusterfly.ru";
    private static final int MQTT_PORT = 9991;
    private static final String MQTT_CLIENT_ID = "ClientDB";
    private static final String MQTT_LOGIN = "user_e26b81e5";
    private static final String MQTT_PASS = "pass_3a57aa79";
    private static final String MQTT_TOPIC1 = "user_e26b81e5/uptime";
    private static final String MQTT_TOPIC2 = "user_e26b81e5/test";
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(mainApp.class);


    private static class carwash {
        public static final String[] id = {"0000.000.00000", "0001.001.00001", "0001.001.00002", "0002.001.00001", "0002.002.00001", "0002.002.00002", "0002.003.00001", "0002.003.00002", "0003.001.00001", "0003.002.00001"};
        public static final String[] workStatus = {"test", "work", "work", "work", "work", "work", "work", "work", "work", "work"};
        public static final String[] typePostStatus = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
        public static final int[] NumberPost = {4, 4, 2, 8, 6, 8, 5, 6, 7, 6};
    }

    private static class MQTTCallback implements MqttCallback {
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

    public static void main(String[] args) {
        long cashIncome = 0;
        long bankIncome = 0;
        while (true) {
            try {
                IMqttClient mqttClient = new MqttClient(MQTT_SERVER + ":" + MQTT_PORT, MQTT_CLIENT_ID);
                MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
                mqttConnectOptions.setAutomaticReconnect(false);
                mqttConnectOptions.setCleanSession(true);
                mqttConnectOptions.setConnectionTimeout(5);
                mqttConnectOptions.setUserName(MQTT_LOGIN);
                mqttConnectOptions.setPassword(MQTT_PASS.toCharArray());

                mqttClient.setCallback(new MQTTCallback());
                mqttClient.connect(mqttConnectOptions);
                mqttClient.subscribe(MQTT_TOPIC1);
                mqttClient.subscribe(MQTT_TOPIC2);

                int i = 0;

                while (true) {

                    LinkedHashMap<String, String> carWashData = new LinkedHashMap<>();

                    carWashData.put("CarWashId", carwash.id[i]);
                    carWashData.put("WorkStatus", carwash.workStatus[i]);
                    String postStatus = "";
                    for (int j = 0; j < carwash.NumberPost[i]; j++) {
                        postStatus = postStatus.concat(carwash.typePostStatus[(int) (Math.random() * 10)]);
                    }
                    carWashData.put("PostStatus", postStatus);

                    cashIncome += (int) (Math.random() * 100);
                    bankIncome += (int) (Math.random() * 100);

                    carWashData.put("CashIncome", Long.toString(cashIncome));
                    carWashData.put("BankIncome", Long.toString(bankIncome));

                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        String jsonString = objectMapper.writeValueAsString(carWashData);
                        MqttMessage mqttMsg = new MqttMessage(jsonString.getBytes());
                        mqttMsg.setQos(0);
                        mqttMsg.setRetained(true);
                        mqttClient.publish(MQTT_TOPIC1, mqttMsg);
                        logger.debug("Send message: {}", mqttMsg.toString());

                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }

                    TimeUnit.SECONDS.sleep(10);
                    i++;
                    if (i == 10) i = 0;
                }

            } catch (MqttException ex) {
                logger.error("MQTT error: {}", ex.toString());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}



