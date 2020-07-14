package com.inder.SpringCamelRoute.route;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@RunWith(CamelSpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@DisableJmx
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
class RouteTest extends CamelTestSupport {

	@Produce(uri = "jms:queue:testinput")
	protected ProducerTemplate start2;

	/*
	 * @EndpointInject(uri = "jms:queue:testoutput") protected MockEndpoint mockB;
	 */

	@Test
	public void testMocksAreValid() throws Exception {
		//mockB.expectedMessageCount(1);
		start2.sendBody("jagga");

	}
}
