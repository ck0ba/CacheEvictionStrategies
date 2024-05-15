package ck0ba.org;

import java.util.LinkedHashMap;
import java.util.Map;

public class MRUCache<K, V> implements Cache<K, V> {
    private final Map<K, V> map;
    private final int capacity;


    public MRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<K, V>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return false; // MRU removal is handled explicitly
            }
        };
    }

    @Override
    public void put(K key, V value) {
        if (map.size() >= capacity && !map.containsKey(key)) {
            remove(getMostRecentlyUsedKey());
        }


        map.put(key, value);
    }

    private K getMostRecentlyUsedKey() {
        K lastKey = null;
        for (K key : map.keySet()) {
            lastKey = key;
        }

        return lastKey;
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
