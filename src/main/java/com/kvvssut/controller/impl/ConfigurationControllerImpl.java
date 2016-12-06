package com.kvvssut.controller.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.kvvssut.caching.CacheManager;
import com.kvvssut.controller.ConfigurationController;
import com.kvvssut.integration.constants.RestQueueConstants;
import com.kvvssut.model.ConfigurationData;

public class ConfigurationControllerImpl implements ConfigurationController {

	@PersistenceContext(unitName = "restapplication_persistence", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	@Inject
	private CacheManager cacheManager;

	@Override
	public String getValueForKey(String configName) {
		String value = null;
		Object object = this.cacheManager.get(RestQueueConstants.CACHE_CONFIGURATION_NAME
		          + configName);

		if (object != null) {
			value = String.valueOf(object);
		} else {
			try {
				value = ((ConfigurationData) entityManager.find(ConfigurationData.class,
				          configName)).getConfigValue();
				System.out.println("cached");
				this.cacheManager.put(RestQueueConstants.CACHE_CONFIGURATION_NAME + configName,
				          value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

}
