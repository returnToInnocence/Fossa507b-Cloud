package com.zenq.cloud507b.util;

import com.zenq.cloud507b.config.MqttConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttUtil {
    public static void mqttConnect(){
        //公共连接部分
        try {
            MqttConfig.client = new MqttClient(MqttConfig.broker, MqttConfig.clientid, new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }
        // 连接参数
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置用户名和密码
        options.setUserName(MqttConfig.username);
        options.setPassword(MqttConfig.password.toCharArray());
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(60);

        // 设置回调
        MqttConfig.client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost: " + cause.getMessage());
            }
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println("接收到返回数据~" + "\nQos: " + message.getQos()
                        + "\ntopic: " + topic + "\nmessage content: "
                        + new String(message.getPayload()));
                MqttConfig.multithreadingConfig.lock.lock();
                MqttConfig.getRecvData = new String(message.getPayload());
                MqttConfig.multithreadingConfig.condition.signal();
                MqttConfig.multithreadingConfig.lock.unlock();
            }
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }
        });
        // 连接
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
        //关闭连接
        try {
            MqttConfig.client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        //关闭客户端
        try {
            MqttConfig.client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    /**
     * Hex转byte,hex只能含两个字符，如果是一个字符byte高位会是0
     */
    public static byte hexTobyte(String hex) {
        return (byte)Integer.parseInt(hex, 16);
    }
    /**
     * Hex转byte[]，两种情况，Hex长度为奇数最后一个字符会被舍去
     */
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
