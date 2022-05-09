package com.program.dictionary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ConfigurationPropertiesScan
@PropertySource(value = "classpath:application.yml", factory = SourceProperty.class)
public class Config {

    @Value("${oxford.urlTranslate}")
    private String urlTranslate;

    @Value("${timeout-connection-url}")
    private int timeoutConnectionUrl;


    public String getUrlTranslate() {
        return urlTranslate;
    }

    public void setUrlTranslate(String urlTranslate) {
        this.urlTranslate = urlTranslate;
    }

    public int getTimeoutConnectionUrl() {
        return timeoutConnectionUrl;
    }

}
