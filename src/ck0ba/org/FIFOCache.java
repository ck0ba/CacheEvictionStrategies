package ck0ba.org;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIFOCache<K, V> implements Cache<K, V> {
    private final Map<K, V> cache;
    private final int capacity;

    public FIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            K firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey);
        }
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
