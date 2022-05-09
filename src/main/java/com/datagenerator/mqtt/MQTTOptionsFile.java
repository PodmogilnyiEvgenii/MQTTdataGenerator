package com.datagenerator.mqtt;

import com.datagenerator.main.Options;
import java.io.*;
import java.util.Map;

public class MQTTOptionsFile {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MQTTOptionsFile.class);

    public static void create() {
        try {
            FileWriter mqttOptionsFileWriter = new FileWriter(Options.filePath);
            for (Map.Entry<String, String> entry : Options.optionsMap.entrySet()) {
                mqttOptionsFileWriter.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            mqttOptionsFileWriter.flush();
            logger.debug("MQTT options file created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            File mqttOptionsFile = new File(Options.filePath);
            FileReader fr = new FileReader(mqttOptionsFile);
            BufferedReader mqttOptionsFileReader = new BufferedReader(fr);

            String buffer = "";
            do {
                buffer = mqttOptionsFileReader.readLine();
                if (buffer != null) {
                    Options.optionsMap.put(buffer.split("=")[0], buffer.split("=")[1]);
                }
            } while (buffer != null);
            logger.debug("MQTT options file readed.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean optionsFileFound() {
        return new File(Options.filePath).exists();
    }
}
