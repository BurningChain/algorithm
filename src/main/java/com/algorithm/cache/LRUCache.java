package com.algorithm.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<String, Object> {

    private Integer maxSize;

    private static final Integer DEFAULT_MAX_SIZE = 100;

    public LRUCache(int initialCapacity, float loadFactor, Integer maxSize) {
        super(initialCapacity, loadFactor);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
        return size() > maxSize;
    }

    @Override
    public Object get(Object key) {
        return super.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }
}
