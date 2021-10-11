package com.invegorant.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:environment/envDev.properties"})
public interface WebConfig extends org.aeonbits.owner.Config {
    String baseUrl();
    String login();
    String password();
}
