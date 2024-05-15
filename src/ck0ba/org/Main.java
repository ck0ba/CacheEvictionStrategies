package ck0ba.org;

public class Main {
    public static void main(String[] args) {
        testCache(new FIFOCache<>(3), "FIFO Cache");
        testCache(new LIFOCache<>(3), "LIFO Cache");
        testCache(new LRUCache<>(3), "LRU Cache");
        testCache(new MRUCache<>(3), "MRU Cache");
        testCache(new LFUCache<>(3), "LFU Cache");
        testCache(new RRCache<>(3), "RR Cache");
    }

    private static void testCache(Cache<Integer, String> cache, String cacheType) {
        System.out.println("Testing " + cacheType);

        // Populate the cache
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        System.out.println("Initial Cache Content:");
        printCache(cache);

        // Access some elements
        System.out.println("Accessed 1: " + cache.get(1));
        System.out.println("Accessed 2: " + cache.get(2));

        // Trigger eviction by adding another element
        System.out.println("Adding element 4: Four");
        cache.put(4, "Four");

        System.out.println("Cache Content after adding element 4:");
        printCache(cache);

        // Remove an element
        System.out.println("Removing element 2");
        cache.remove(2);
        printCache(cache);

        System.out.println();
    }

    private static void printCache(Cache<Integer, String> cache) {
        if (cache.size() == 0) {
            System.out.println("Cache is empty");
        } else {
            for (int i = 1; i <= cache.size() + 1; i++) {
                String value = cache.get(i);
                if (value != null) {
                    System.out.println("Key: " + i + ", Value: " + value);
                }
            }
        }
    }

}
