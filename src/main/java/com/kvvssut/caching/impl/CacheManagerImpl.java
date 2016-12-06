package com.kvvssut.caching.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

import com.kvvssut.caching.CacheManager;

@Singleton
public class CacheManagerImpl implements CacheManager {

	private static Logger logger = Logger.getLogger(CacheManagerImpl.class);

	private static JCS objectCache;
	private static final String CACHE_REGION = "simpleObjectCache";

	@PostConstruct
	private void init() {
		try {
			objectCache = JCS.getInstance(CACHE_REGION);
		} catch (CacheException ce) {
			logger.error("init-cache: " + ce.getMessage());
		}
	}

	@Override
	public void put(String name, Object object) {
		if (objectCache != null) {
			try {
				objectCache.put(name, object);
			} catch (CacheException ce) {
				logger.error("put-cache: " + ce.getMessage());
			}
		}
	}

	@Override
	public Object get(String name) {
		Object object = null;
		if (objectCache != null) {
			object = objectCache.get(name);
		}
		return object;
	}

}
