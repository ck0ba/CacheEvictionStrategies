package ck0ba.org;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    @Override
    public void put(K key, V value) {
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
