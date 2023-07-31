package com.zenq.cloud507b.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.stereotype.Component;

@Component
public class MqttConfig {
    public static String broker = "tcp://124.222.87.124:1883";
    public static String sendTopic = "lihongjun/subscribe";
    public static String subscribeTopic = "lihongjun/send";
    public static String username = "usrLi";
    public static String password = "usrLi";
    public static String clientid = "spring";
    public static MqttClient client = null;
    public static int qos = 0;
    public static String getRecvData = null;
    public static MultithreadingConfig multithreadingConfig = new MultithreadingConfig();


}
