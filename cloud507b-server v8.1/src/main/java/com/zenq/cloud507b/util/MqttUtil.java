package com.zenq.cloud507b.util;

import com.zenq.cloud507b.config.MqttConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttUtil {
    public static void mqttConnect(){
        try {
            MqttConfig.client = new MqttClient(MqttConfig.broker, MqttConfig.clientid, new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(MqttConfig.username);
        options.setPassword(MqttConfig.password.toCharArray());
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(60);
        MqttConfig.client.setCallback(new MqttCallback() {
            //实现重连机制
            @Override
            public void connectionLost(Throwable cause) {
//                System.out.println("connectionLost: " + cause.getMessage());
                System.out.println("连接丢失，原因：" + cause.getMessage());
                // 在此处进行重连操作
                try {
                    MqttConfig.client.connect(options);
                    // 重新订阅主题等操作
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
            public void messageArrived(String topic, MqttMessage message) {
//                System.out.println("接收到返回数据~" + "\nQos: " + message.getQos()
//                        + "\ntopic: " + topic + "\nmessage content: "
//                        + new String(message.getPayload()));
                MqttConfig.multithreadingConfig.lock.lock();
                MqttConfig.getRecvData = new String(message.getPayload());
                MqttConfig.multithreadingConfig.condition.signal();
                MqttConfig.multithreadingConfig.lock.unlock();
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
//                System.out.println("deliveryComplete---------" + token.isComplete());
            }
        });
        try {
            MqttConfig.client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        System.out.println("连接成功");
        try {
            MqttConfig.client.subscribe(MqttConfig.subscribeTopic, MqttConfig.qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void mqttDisconnect(){
        try {
            MqttConfig.client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        try {
            MqttConfig.client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static byte hexTobyte(String hex) {
        return (byte)Integer.parseInt(hex, 16);
    }

    public static byte[] hexTobytes(String hex) {
        if (hex.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hex.length() / 2];
            int j = 0;
            for(int i = 0; i < hex.length(); i+=2) {
                result[j++] = (byte)Integer.parseInt(hex.substring(i,i+2), 16);
            }
            return result;
        }
    }


}
