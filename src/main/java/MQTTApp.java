import java.util.concurrent.TimeUnit;

public class MQTTApp {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MainApp.class);

    public static void activate() {
        MQTTOptions.getOptions();
        MQTTConnect.connect();
        int i = 0;
        while (true) {
            if (MQTTConnect.mqttClient.isConnected()) {
                MQTTSendMessage.send(i);
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i == 10) i = 0;
        }
    }
}
