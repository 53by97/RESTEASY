package com.kvvssut.caching;

public interface CacheManager {

	public abstract void put(String name, Object object);

	public abstract Object get(String name);
}
