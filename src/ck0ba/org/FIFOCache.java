package ck0ba.org;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIFOCache<K, V> implements Cache<K, V> {
    private final Map<K, V> map;
    private final int capacity;


    public FIFOCache(int capacity) {
        this.capacity = capacity;

        this.map = new LinkedHashMap<>(16, 0.75f, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > FIFOCache.this.capacity;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }
}
