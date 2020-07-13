package com.inder.SpringCamelRoute;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.jms.*;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = SpringCamelRouteApplication.class)
@ActiveProfiles("test")
public class SpringCamelRouteApplicationTests {

/*	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${camel.routes.advice.inputqueueurl}")
	private String inQueueName;

	@Value("${camel.routes.advice.outputqueueurl}")
	private String outQueueName;*/

	/*@Rule
	public EmbeddedActiveMQBroker embeddedActiveMQBroker = new EmbeddedActiveMQBroker() {
		@Override
		protected void configure() {
			try {
				this.getBrokerService().addConnector("vm://embedded?broker.persistent=false?useJmx=true");
			} catch (Exception e) {

			}
		}
	};*/

	private static BrokerService brokerService = new BrokerService();
	private static Queue inputQ;
	private static Session session;

	@Value("${camel.routes.advice.inputqueueurl}")
	private String inQueueName;


	//Sets up a embedded broker.
	public static void setUpBroker() throws Exception {
		brokerService.setBrokerName("TestBroker");
		brokerService.addConnector("vm://embedded");
		brokerService.setPersistent(false);
		brokerService.setUseJmx(false);
		brokerService.start();

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://" + brokerService.getBrokerName() + "?create=false");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		inputQ = session.createQueue("${camel.routes.advice.inputqueueurl}");
	}
	private void stopBroker() throws Exception {
		brokerService.stop();
		brokerService.waitUntilStopped();
	}


	@Test
	public void contextLoads() throws Exception {
		System.out.println("Context Load Test Passed");
		stopBroker();
		setUpBroker();

/*		jmsTemplate.convertAndSend(inQueueName, "Message from Test Queue.");
		jmsTemplate.receive(outQueueName);*/

		MessageProducer messageProducer = session.createProducer(inputQ);
		messageProducer.send(session.createTextMessage("Test Message from Test Method."));


		System.out.println("VM : " + brokerService.getBrokerName());


	}
}
