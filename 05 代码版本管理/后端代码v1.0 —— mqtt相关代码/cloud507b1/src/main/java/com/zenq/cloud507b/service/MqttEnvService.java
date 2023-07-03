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

        // 创建消息并设置 QoS
        MqttMessage message = new MqttMessage(hexBytes);
        message.setQos(MqttConfig.qos);
        //先加锁
        MqttConfig.multithreadingConfig.lock.lock();
        // 发布消息
        try {
            MqttConfig.client.publish(MqttConfig.sendTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        System.out.println("消息发布");
        //此时下面应该是等待回调以后再返回回调的结果
        System.out.println("同步等待过程");
        try {
            MqttConfig.multithreadingConfig.condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MqttConfig.multithreadingConfig.lock.unlock();
        //获取到数据后进行解析处理
        int res = Integer.parseInt(MqttConfig.getRecvData.substring(begin,end), 16);

        return res;
    }

    public void controlSend(String hexMes) {
        byte[] hexBytes = MqttUtil.hexTobytes(hexMes);

        // 创建消息并设置 QoS
        MqttMessage message = new MqttMessage(hexBytes);
        message.setQos(MqttConfig.qos);

        // 发布消息
        try {
            MqttConfig.client.publish(MqttConfig.sendTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        System.out.println("消息发布");
        return;
    }
}
