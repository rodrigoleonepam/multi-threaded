package com.epam.task;

import java.util.LinkedHashMap;

/**
 * LRU Cache Implementation Implement an LRU (Least Recently Used) Cache using 
 * Java 8's LinkedHashMap to store key-value pairs.
 */
public class LRUCache {

    private LinkedHashMap<String, String> cache;

    public LRUCache() {
        cache = new LinkedHashMap<>();
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache();
        cache.put("hello", "world");
        cache.put("foo", "bar");
        System.out.println("Cache: " + cache.get("foo"));
    }

}
