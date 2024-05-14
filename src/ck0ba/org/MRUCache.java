package ck0ba.org;

import java.util.LinkedHashMap;
import java.util.Map;

public class MRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private final int capacity;

    public MRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (size() > capacity) {
            remove(getLastKey());
            return true;
        }
        return false;
    }

    private K getLastKey() {
        K lastKey = null;
        for (K key : this.keySet()) {
            lastKey = key;
        }
        return lastKey;
    }

    @Override
    public void put1(K key, V value) {
        super.put(key, value);
    }

    @Override
    public V get(K key) {
        return super.get(key);
    }

    @Override
    public void remove(K key) {
        super.remove(key);
    }

    @Override
    public int size() {
        return super.size();
    }
}
