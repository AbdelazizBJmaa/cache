package com.kata.cache.model;

import java.util.Objects;

public class CacheModel {
     private Object value;
     private long TTL;
     
    public CacheModel(Object o , long ttl) {
    	this.value = o;
    	this.TTL = ttl;
    }
	public Object getValue() {
		return value;
	}
	public long getTTL() {
		return TTL;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public void setTTL(long tTL) {
		TTL = tTL;
	}
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheModel other = (CacheModel) obj;
		return Objects.equals(value, other.value);
	}
     
     
}
