package org.abhishek.lrucache.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LRUCacheDriver {

    public static void main(String[] args) throws InterruptedException {

        LRUCache<String, String> lruCache = new LRUCache<>(2);

        lruCache.addKey("abc", "1");
        lruCache.addKey("def", "2");
        System.out.println(lruCache.getKey("abc"));
        lruCache.addKey("ghi", "3");
        System.out.println(lruCache.getKey("def"));
        System.out.println(lruCache.getKey("abc"));
        System.out.println(lruCache.getKey("ghi"));

        lruCache.addKey("jkl", "4", 1L);
        Thread.sleep(500);
        System.out.println(lruCache.getKey("jkl"));
        Thread.sleep(1000);
        System.out.println(lruCache.getKey("jkl"));

        //Stress testing
        LRUCacheWithSegment<Integer, String> lruCacheWithSegment = new LRUCacheWithSegment<>(10, 2);

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        CountDownLatch latch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            int key = i % 50;
            executorService.submit(() -> {
                try {
                    lruCacheWithSegment.addKey(key, key + "-val");
                    lruCacheWithSegment.getKey(key);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();


    }
}
