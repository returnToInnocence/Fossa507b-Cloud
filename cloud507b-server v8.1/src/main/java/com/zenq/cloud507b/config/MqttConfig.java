package com.zenq.cloud507b.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.stereotype.Component;

@Component
public class MqttConfig {
    public static String broker = "xxx";
    public static String sendTopic = "xxx";
    public static String subscribeTopic = "xxx";
    public static String username = "xxx";
    public static String password = "xxx";
    public static String clientid = "xxx";
    public static MqttClient client = null;
    public static int qos = 0;
    public static String getRecvData = null;
    public static MultithreadingConfig multithreadingConfig = new MultithreadingConfig();


}
