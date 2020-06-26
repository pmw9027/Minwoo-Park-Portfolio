package com.rival.hs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minwoo on 2017. 4. 5..
 */

@Configuration
@ConfigurationProperties
@PropertySource("classpath:/application.yml")
public class Holder {

    String test;

    public Map<String, Map<String, String>> servers = new HashMap<String, Map<String, String>>();
    public Map<String, Map<String, String>> stadium = new HashMap<String, Map<String, String>>();

    public Map<String, Map<String, String>> getServers() {
        return servers;
    }

    public Map<String, Map<String, String>> getStadium() {
        return stadium;
    }

    public String getTest() {
        return test;
    }



}
