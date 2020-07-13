package com.inder.SpringCamelRoute.route;

import com.inder.SpringCamelRoute.properties.ApplicationProperties;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {

     private ApplicationProperties applicationProperties;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void configure() throws Exception {

        System.out.println("Configuring route");

        from(applicationProperties.getINPUTQURL())
                .to(applicationProperties.getOUTPUTQURL())
                .log("Message sent to the other queue")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Message Sent...!");
                    }
                })
                .end();
    }
}
