package ck0ba.org;

public class TestingCaches {
    public static void main(String[] args) {
        testCache(new FIFOCache<Integer, String>(3), "FIFO");
        testCache(new LIFOCache<Integer, String>(3), "LIFO");
        testCache(new LRUCache<Integer, String>(3), "LRU");
        testCache(new MRUCache<Integer, String>(3), "MRU");
        testCache(new LFUCache<Integer, String>(3), "LFU");
        testCache(new RRCache<Integer, String>(3), "RR");
    }

    private static void testCache(Cache<Integer, String> cache, String type) {
        System.out.println("Testing " + type + " ck0ba.org.Cache");

        // Add entries to the cache
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        System.out.println("Initial ck0ba.org.Cache: ");
        printCache(cache);

        // Access some elements
        System.out.println("Accessing 1: " + cache.get(1));
        System.out.println("Accessing 2: " + cache.get(2));

        // Add another element to cause eviction
        System.out.println("Adding 4:Four");
        cache.put(4, "Four");

        System.out.println("ck0ba.org.Cache after adding 4: ");
        printCache(cache);

        // Test removing element
        System.out.println("Removing key 2");
        cache.remove(2);
        printCache(cache);

        System.out.println("--------------------\n");
    }

    private static void printCache(Cache<Integer, String> cache) {
        if (cache instanceof LRUCache || cache instanceof MRUCache) {
            LinkedHashMap<Integer, String> map = (LinkedHashMap<Integer, String>) cache;
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        } else if (cache instanceof LFUCache) {
            LFUCache<Integer, String> lfu = (LFUCache<Integer, String>) cache;
            for (Map.Entry<Integer, String> entry : lfu.cache.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue() + ", Frequency: " + lfu.frequencies.get(entry.getKey()));
            }
        } else {
            // Generic print for other caches
            for (Integer key : cache.keySet()) {
                System.out.println("Key: " + key + ", Value: " + cache.get(key));
            }
        }
    }
}
