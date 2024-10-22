package com.kata.cache.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache {
    private Map<Long,CacheModel> mapcache;
    
    private static InMemoryCache inMemoryCache = null;
    
    private InMemoryCache() {
    	mapcache = new ConcurrentHashMap<Long, CacheModel>();
    }
    
    public static InMemoryCache getInstance() {
    	if(Objects.isNull(inMemoryCache)) {
    		inMemoryCache = new InMemoryCache();
    	}
    	return inMemoryCache;
    }

	public Map<Long, CacheModel> getMapcache() {
		return mapcache;
	}
    
    
}
