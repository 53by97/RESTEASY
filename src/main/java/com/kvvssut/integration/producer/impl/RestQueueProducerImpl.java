package com.kvvssut.integration.producer.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.codehaus.jackson.map.ObjectMapper;

import com.kvvssut.integration.constants.RestQueueConstants;
import com.kvvssut.integration.dto.RestQueuePayload;
import com.kvvssut.integration.producer.RestQueueProducer;
import com.kvvssut.service.ConfigurationService;

public class RestQueueProducerImpl implements RestQueueProducer {

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/queue/restTestQueue")
	private Queue queue;

	@Inject
	private ConfigurationService configurationService;

	private ObjectMapper objectMapper;

	@PostConstruct
	private void init() {
		objectMapper = new ObjectMapper();
	}

	@Override
	public void putMessage(RestQueuePayload restQueueDTO) throws Exception {
		Session session = null;
		MessageProducer messageProducer = null;
		Connection connection = null;

		try {
			connection = this.connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			connection.start();

			TextMessage textMessage = session.createTextMessage(objectMapper
			          .writeValueAsString(restQueueDTO));
			String executionTime = configurationService
			          .getValueByConfigurationKey(RestQueueConstants.CONFIG_QUEUE_EXECUTION_TIME);
			long timeInMilliSeconds = this.calculateScheduledTime(Integer
			          .parseInt(executionTime));
			textMessage.setLongProperty("_HQ_SCHED_DELIVERY", timeInMilliSeconds); 	// It's the delivery time, not delay!
			messageProducer.send(textMessage);
		} finally {
			if (messageProducer != null) {
				messageProducer.close();
				session.close();
				connection.close();
			} else if (session != null) {
				session.close();
				connection.close();
			} else if (connection != null) {
				connection.close();
			}
		}
	}

	private long calculateScheduledTime(int queueExecutionTimeInMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, queueExecutionTimeInMinutes);
		return calendar.getTimeInMillis();
	}

}
