package ck0ba.org;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class LIFOCache<K, V> implements Cache<K, V> {
    private final Map<K, V> cache;
    private final Stack<K> stack;
    private final int capacity;

    public LIFOCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.stack = new Stack<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            K lastKey = stack.pop();
            cache.remove(lastKey);
        }
        if (!cache.containsKey(key)) {
            stack.push(key);
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
        stack.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
