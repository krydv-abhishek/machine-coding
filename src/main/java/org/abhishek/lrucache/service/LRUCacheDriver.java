package org.abhishek.lrucache.service;

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

    }
}
