package com.zenq.cloud507b.service;

import com.zenq.cloud507b.config.MqttConfig;
import com.zenq.cloud507b.util.MqttUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttEnvService {
    public int getAndParseData(String hexMes, int begin, int end) {
        byte[] hexBytes = MqttUtil.hexTobytes(hexMes);
        MqttMessage message = new MqttMessage(hexBytes);
        message.setQos(MqttConfig.qos);
        MqttConfig.multithreadingConfig.lock.lock();
        try {
            MqttConfig.client.publish(MqttConfig.sendTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
//        System.out.println("消息发布");
//        System.out.println("同步等待过程");
        try {
            MqttConfig.multithreadingConfig.condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MqttConfig.multithreadingConfig.lock.unlock();
        int res = Integer.parseInt(MqttConfig.getRecvData.substring(begin,end), 16);
        return res;
    }

    public void controlSend(String hexMes) {
        byte[] hexBytes = MqttUtil.hexTobytes(hexMes);
        MqttMessage message = new MqttMessage(hexBytes);
        message.setQos(MqttConfig.qos);
        try {
            MqttConfig.client.publish(MqttConfig.sendTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
//        System.out.println("消息发布");
        return;
    }
}
