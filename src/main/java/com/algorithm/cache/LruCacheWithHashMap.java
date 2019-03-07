package com.algorithm.cache;

import java.util.HashMap;
import java.util.Map;

public class LruCacheWithHashMap<K, V> {

    private Integer maxSize;
    private Entry<K, V> first;
    private Entry<K, V> last;
    private Map<K, Entry<K, V>> cache;

    public LruCacheWithHashMap(Integer maxSize) {
        this.maxSize = maxSize;
        cache = new HashMap<>();
    }

    public LruCacheWithHashMap(Integer maxSize, Map<K, Entry<K, V>> cache) {
        this.maxSize = maxSize;
        this.cache = cache;
    }

    public void put(K key, V value) {
        Entry<K, V> entry = getEntry(key);
        if (null == entry) {
            if (cache.size() >= maxSize) {
                cache.remove(last.key);
            }
            entry = new Entry<>();
            entry.key = key;
        }
        entry.value = value;
        moveToFirst(entry);
        cache.put(key, entry);
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (null == entry) {
            return null;
        }

        moveToFirst(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (null == entry) {
            return;
        }
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        if (entry == first) {
            first = first.next;
        }
        if (entry == last) {
            last = last.pre;
        }

        cache.remove(key);
    }


    private void moveToFirst(Entry<K, V> entry) {
        if (entry == first) {
            return;
        }
        if (null == first || last == null) {
            first = last = entry;
            return;
        }

        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }
        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }
        if (entry == last) {
            last = last.pre;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    private void removeLast(Entry<K, V> last) {
        if (null != last) {
            last = last.pre;
            if (null == last) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }


    private Entry<K, V> getEntry(K key) {
        return cache.get(key);
    }

    class Entry<K, V> {
        public Entry pre;
        public Entry next;
        public K key;
        public V value;
    }
}
