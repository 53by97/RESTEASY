package com.kvvssut.thread;

import com.kvvssut.integration.dto.RestQueuePayload;
import com.kvvssut.integration.producer.RestQueueProducer;

public class RestJobThread implements Runnable {

	private RestQueueProducer producer;
	private RestQueuePayload restQueueDTO;

	public RestJobThread(RestQueueProducer producer, RestQueuePayload restQueueDTO) {
		this.producer = producer;
		this.restQueueDTO = restQueueDTO;
	}

	@Override
	public void run() {
		try {
			System.out.println(producer + " " + restQueueDTO);
			producer.putMessage(restQueueDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}