package ck0ba.org;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LIFOCache<K, V> implements Cache<K, V> {
    private final Map<K, V> map;
    private final LinkedList<K> stack;
    private final int capacity;


    public LIFOCache(int capacity) {
        this.capacity = capacity;

        this.map = new HashMap<>();
        this.stack = new LinkedList<>();
    }

    @Override
    public void put(K key, V value) {
        if (map.size() >= capacity && !map.containsKey(key)) {
            K lastKey = stack.removeFirst();
            map.remove(lastKey);
        }

        if (!map.containsKey(key)) {
            stack.addFirst(key);
        }

        map.put(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
        stack.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }
}
