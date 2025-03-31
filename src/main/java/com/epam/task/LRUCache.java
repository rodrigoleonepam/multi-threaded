package com.epam.task;

import java.util.LinkedHashMap;

/**
 * LRU Cache Implementation Implement an LRU (Least Recently Used) Cache using 
 * Java 8's LinkedHashMap to store key-value pairs.
 */
public class LRUCache {

    private LinkedHashMap<String, CacheItem> cache;

    public LRUCache() {
        cache = new LinkedHashMap<>();
    }

    public void put(String key, CacheItem value) {
        value.setTtl(System.currentTimeMillis() + value.getTtl());
        cache.put(key, value);
    }

    public CacheItem get(String key) {
        long currentTime = System.currentTimeMillis();
        CacheItem item = cache.get(key);
        if (item == null || item.getTtl() < currentTime) {
            cache.remove(key);
            item.setValue(null);
            return item;
        }
        return cache.get(key);
    }

    public static void main(String[] args) throws Exception {
        LRUCache cache = new LRUCache();
        cache.put("hello", new CacheItem("world", 60000)); // 1 minute TTL
        cache.put("expired", new CacheItem("expired", 10)); // 10 ms TTL
        Thread.sleep(10000); // Simulate time passing
        System.out.println("Cache: " + cache.get("hello").getValue());
        System.out.println("Cache: " + cache.get("expired").getValue());
    }

}
