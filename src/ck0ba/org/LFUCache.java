package ck0ba.org;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;
    private final Map<K, Integer> frequencies;
    private final Map<Integer, LinkedHashSet<K>> frequencyList;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencies = new HashMap<>();
        this.frequencyList = new HashMap<>();
        this.minFrequency = 1;
    }

    @Override
    public void put1(K key, V value) {
        if (capacity == 0) return;
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key); // update frequency
            return;
        }
        if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        frequencies.put(key, 1);
        frequencyList.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFrequency = 1;
    }

    private void evict() {
        K evictKey = frequencyList.get(minFrequency).iterator().next();
        frequencyList.get(minFrequency).remove(evictKey);
        cache.remove(evictKey);
        frequencies.remove(evictKey);
    }

    @Override
    public V get(K key) {
        if (!cache.containsKey(key)) return null;
        int freq = frequencies.get(key);
        frequencyList.get(freq).remove(key);
        freq++;
        frequencies.put(key, freq);
        frequencyList.computeIfAbsent(freq, k -> new LinkedHashSet<>()).add(key);
        if (frequencyList.get(minFrequency).isEmpty()) minFrequency++;
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        if (!cache.containsKey(key)) return;
        int freq = frequencies.get(key);
        frequencyList.get(freq).remove(key);
        if (frequencyList.get(freq).isEmpty() && freq == minFrequency) minFrequency++;
        frequencies.remove(key);
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
