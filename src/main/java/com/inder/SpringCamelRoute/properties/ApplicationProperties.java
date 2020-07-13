package com.inder.SpringCamelRoute.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class ApplicationProperties {

    @Value("${camel.routes.advice.inputqueueurl}")
    private String INPUTQURL;

    @Value("${camel.routes.advice.outputqueueurl}")
    private String OUTPUTQURL;
}

