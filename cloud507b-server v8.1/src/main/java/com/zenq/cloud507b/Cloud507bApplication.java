package com.zenq.cloud507b;

import com.zenq.cloud507b.util.MqttUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cloud507bApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cloud507bApplication.class, args);
        MqttUtil.mqttConnect();
    }

}
