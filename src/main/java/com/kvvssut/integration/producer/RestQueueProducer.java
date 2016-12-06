package com.kvvssut.integration.producer;

import com.kvvssut.integration.dto.RestQueuePayload;

public interface RestQueueProducer {
	public void putMessage(RestQueuePayload restQueueDTO) throws Exception;
}
