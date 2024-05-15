package ck0ba.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RRCache<K, V> implements Cache<K, V> {
    private final Map<K, V> map;
    private final ArrayList<K> keys;
    private final int capacity;
    private final Random rand;


    public RRCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.keys = new ArrayList<>();
        this.rand = new Random();
    }

    @Override
    public void put(K key, V value) {
        if (map.size() >= capacity && !map.containsKey(key)) {
            int randomIndex = rand.nextInt(keys.size());
            K randomKey = keys.remove(randomIndex);
            map.remove(randomKey);
        }

        if (!map.containsKey(key)) {
            keys.add(key);
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
        keys.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }
}
