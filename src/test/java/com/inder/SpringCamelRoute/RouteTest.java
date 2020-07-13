package com.inder.SpringCamelRoute;

import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = SpringCamelRouteApplication.class)
@ActiveProfiles("test")
public class RouteTest {

    /*
    Embedded ActiveMQ
    Connection Factory
    Create Queue
     */
  @Test
  public void contextLoads() throws Exception {
      System.out.println("Context Load Test Passed");
  }

}
