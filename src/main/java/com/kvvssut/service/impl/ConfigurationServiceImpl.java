package com.kvvssut.service.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.kvvssut.controller.ConfigurationController;
import com.kvvssut.service.ConfigurationService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConfigurationServiceImpl implements ConfigurationService {

	@Inject
	private ConfigurationController configurationController;

	@Override
	public String getValueByConfigurationKey(String key) {
		return this.configurationController.getValueForKey(key);
	}

}
