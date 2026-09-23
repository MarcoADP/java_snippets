package org.example;

import org.example.hashmap.ConcurrentHashMap;
import org.example.hashmap.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        testHashMap();
        testThreadHashMap();
        testConcurrentHashMap();
    }

    private static void testConcurrentHashMap() throws InterruptedException {
        var concurrentMap = new ConcurrentHashMap<>(5);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                concurrentMap.put(i, "%s-%s".formatted(1, i));
                System.out.println("Thread 1 added: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                concurrentMap.put(i, "%s-%s".formatted(2, i));
                System.out.println("Thread 2 added: " + i);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                concurrentMap.put(i, "%s-%s".formatted(3, i));
                System.out.println("Thread 3 added: " + i);
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                var value = concurrentMap.get(i);
                System.out.println("Thread 4 read: " + i + " = " + value);
            }
        });

        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                var value = concurrentMap.get(i);
                System.out.println("Thread 5 read: " + i + " = " + value);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("\n ConcurrentMap:");
        System.out.println(concurrentMap);
    }

    private static void testThreadHashMap() throws InterruptedException {
        var map = new HashMap<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                map.put(i, "%s-%s".formatted(1, i));
                System.out.println("Thread 1 added: " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                map.put(i, "%s-%s".formatted(2, i));
                System.out.println("Thread 2 added: " + i);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 5; i < 10; i++) {
                map.put(i, "%s-%s".formatted(3, i));
                System.out.println("Thread 3 added: " + i);
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                var value = map.get(i);
                System.out.println("Thread 4 read: " + i + " = " + value);
            }
        });

        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                var value = map.get(i);
                System.out.println("Thread 5 read: " + i + " = " + value);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println("\n ConcurrentMap:");
        System.out.println(map);
    }

    private static void testHashMap() {
        var map = new HashMap<String, Integer>(3, 0.75f);
        map.put("Aa", 1);
        map.put("B", 2);
        map.put("C", 1);
        map.put("BB", 22);
        map.put("A", 3);
        System.out.println(map);

        System.out.println("Aa => " + map.get("Aa"));
        System.out.println("BB => " + map.get("BB"));
        System.out.println("CC => " + map.get("CC"));

        System.out.println("Aa => " + map.getOptional("Aa"));
        System.out.println("BB => " + map.getOptional("BB"));
        System.out.println("CC => " + map.getOptional("CC"));

        System.out.println("Aa => " + map.getOptionalOrDefault("Aa", 0));
        System.out.println("BB => " + map.getOptionalOrDefault("BB", 0));
        System.out.println("CC => " + map.getOptionalOrDefault("CC", 0));

        var hashmap = new java.util.HashMap<String, Integer>();
        hashmap.put("A", 1);
        hashmap.put("B", 2);
        hashmap.put("C", 1);
        System.out.println(hashmap);
    }
}