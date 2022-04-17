import java.util.concurrent.TimeUnit;

public class MQTTapp {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(mainApp.class);

    public static void active() {
        MQTToptions.GetOptions();
        MQTTconnect.Connect();
        int i = 0;
        while (true) {
            if (MQTTconnect.mqttClient.isConnected()) {
                MQTTsendMessage.send(i);
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
