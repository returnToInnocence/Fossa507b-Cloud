package com.zenq.cloud507b.config;

public class MqttConfig {
    public String broker = "tcp://broker.emqx.io:1883";
    // TLS/SSL
    // String broker = "ssl://broker.emqx.io:8883";
    public String username = "emqx";
    public String password = "public";
    public String clientid = "publish_client";
}
