package ck0ba.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RRCache<K, V> implements Cache<K, V> {
    private final Map<K, V> cache;
    private final ArrayList<K> keys;
    private final int capacity;
    private final Random rand = new Random();

    public RRCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            return;
        }
        if (cache.size() >= capacity) {
            int removeIndex = rand.nextInt(keys.size());
            K removeKey = keys.get(removeIndex);
            keys.remove(removeIndex);
            cache.remove(removeKey);
        }
        cache.put(key, value);
        keys.add(key);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        keys.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
