package com.kvvssut.service.impl;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvvssut.controller.RestController;
import com.kvvssut.dto.RestRequestDTO;
import com.kvvssut.integration.dto.RestQueuePayload;
import com.kvvssut.integration.producer.RestQueueProducer;
import com.kvvssut.service.RestService;
import com.kvvssut.thread.JobExecutor;
import com.kvvssut.thread.RestJobThread;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RestServiceImpl implements RestService {

	@Inject
	private RestController restController;

	@Inject
	private RestQueueProducer producer;

	private static Logger LOGGER = LoggerFactory.getLogger(RestServiceImpl.class);

	private RestQueuePayload restQueueDTO;

	@Override
	public String checkDetails(String userName, RestRequestDTO restRequestDTO) {

		restQueueDTO = new RestQueuePayload();
		restQueueDTO.setUserName(userName);
		restQueueDTO.setAmount(restRequestDTO.getAmount());
		restQueueDTO.setRetryCounter(0);

		String output = "Success";
		try {
			JobExecutor jobExecutor = new JobExecutor();
			jobExecutor.executor(new RestJobThread(producer, restQueueDTO));
			LOGGER.info("timestamp b4 putting into queue : {}", System.currentTimeMillis());
		} catch (Exception e) {
			output = "Failure";
			e.printStackTrace();
		}

		return output;
	}

	@Override
	public void verifyUserDetails(String userName, BigDecimal amount) {
		LOGGER.info("a4 cal : " + System.currentTimeMillis());
		restController.updateBalanceByUserName(userName, amount);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BigDecimal getBalance(String userName) {
		LOGGER.info("timestamp b4 calling db controller : {}", System.currentTimeMillis());
		return restController.getBalanceByUserName(userName);
	}

}
