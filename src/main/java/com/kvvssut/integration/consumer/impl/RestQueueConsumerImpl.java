package com.kvvssut.integration.consumer.impl;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.kvvssut.integration.dto.RestQueuePayload;
import com.kvvssut.service.RestService;

@MessageDriven(activationConfig = {
          @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
          @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/restTestQueue"),
          @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class RestQueueConsumerImpl implements MessageListener {

	@Inject
	private RestService restService;

	private ObjectMapper objectMapper;

	private static Logger logger = Logger.getLogger(RestQueueConsumerImpl.class);

	@PostConstruct
	private void init() {
		objectMapper = new ObjectMapper();
	}

	@Override
	public void onMessage(Message message) {
		RestQueuePayload restQueuePayload = null;

		if (message instanceof TextMessage) {
			try {
				String textMessage = ((TextMessage) message).getText();
				restQueuePayload = objectMapper.readValue(textMessage, RestQueuePayload.class);
				this.logConsumingMessage(message);
				restService.verifyUserDetails(restQueuePayload.getUserName(),
				          restQueuePayload.getAmount());
			} catch (JMSException | IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	private void logConsumingMessage(Message message) throws JMSException {
		long deliveryTime = System.currentTimeMillis() - message.getJMSTimestamp();
		logger.info("DeliveryTime : " + deliveryTime);

		Destination destination = message.getJMSDestination();
		if (destination != null) {
			logger.info("Destination Queue Name : " + destination.toString());
		}
	}

}
