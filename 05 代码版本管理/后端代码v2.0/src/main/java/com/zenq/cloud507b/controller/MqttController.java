package com.zenq.cloud507b.controller;

import com.zenq.cloud507b.service.MqttEnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {
    @Autowired
    MqttEnvService mqttEnvService;

    @GetMapping("/api/mqttGetAirTmp")
    public double mqttGetAirTmp() {
        String hexMes = "010300010001d5ca";
        double airTemp = mqttEnvService.getAndParseData(hexMes,6,10) * 0.1;
        return airTemp;
    }

    @GetMapping("/api/mqttGetLightValue")
    public int mqttGetLightValue() {
        String hexMes = "01030007000275ca";
        int lightValue = mqttEnvService.getAndParseData(hexMes,6, 14);
        return lightValue;
    }

    @GetMapping("/api/mqttLightOpen")
    public String mqttLightOpen() {
        String hexMes = "02050001ff00ddc9";
        mqttEnvService.controlSend(hexMes);
        return "success";
    }

    @GetMapping("/api/mqttLightClose")
    public String mqttLightClose() {
        String hexMes = "0205000100009c39";
        mqttEnvService.controlSend(hexMes);
        return "success";
    }
}
