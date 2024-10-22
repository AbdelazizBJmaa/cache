package com.kata.cache.model.api;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kata.cache.model.CacheModel;
import com.kata.cache.model.InMemoryCache;

@RestController
@RequestMapping("/cache")
public class RestInterface {

	@PutMapping("/add")
	public long addcache(@RequestBody CacheModel cache) {
		InMemoryCache inMemoryCache = InMemoryCache.getInstance();
		Map<Long, CacheModel> map = inMemoryCache.getMapcache();
		if (Objects.nonNull(map)) {
			Entry<Long, CacheModel> entry = map.entrySet().stream().filter(e -> e.equals(cache.getValue())).findFirst()
					.get();
			Long key = entry.getKey();
			map.merge(key, cache, null);
			return key;
		} else {
			Long max = map.keySet().stream().max(Comparator.naturalOrder()).orElseGet(() -> Long.valueOf(0l));
			Long key = max + 1;
			map.put(max + 1, cache);
			return key;
		}

	}

	@GetMapping("/get")
	 public Object getCache(@RequestParam long key) {
		 Object value = null;
		 InMemoryCache inMemoryCache = InMemoryCache.getInstance();
		 Map<Long,CacheModel> map = inMemoryCache.getMapcache();
		 if(map.containsKey(key)) {
			 Entry<Long,CacheModel> entry = map.entrySet().stream().filter(e -> e.getKey() == key).findFirst().get();
			 LocalDateTime localDate = LocalDateTime.now().plusSeconds(entry.getValue().getTTL());
		     if(localDate.isBefore(LocalDateTime.now()))
		    	 value = entry.getValue().getValue();	
		 }
		 return value;
	 }
}
