import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;

import java.util.concurrent.TimeUnit;
import java.util.*;

public class mainApp {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(mainApp.class);
    public static void main(String[] args) {
        MQTTapp.active();
    }
}



