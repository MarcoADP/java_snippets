package org.example;

import org.example.hashmap.ConcurrentHashMap;
import org.example.hashmap.HashMap;
import org.example.list.ArrayList;
import org.example.list.DoublyLinkedList;
import org.example.list.LinkedList;
import org.example.list.Transaction;
import org.example.queue.PriorityQueue;
import org.example.stack.StackArray;
import org.example.stack.StackLinkedList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        testHashMap();
//        testThreadHashMap();
//        testConcurrentHashMap();
        //testComparator();
        //testPriorityQueue();
        testArrayList();
        //testLinkedList();
        //testStackArray();
    }

    private static void testStackArray() {
        var stackArray = new StackArray<Integer>(1);
        var stackLinkedList = new StackLinkedList<Integer>();

        stackArray.push(1);
        stackArray.push(2);
        stackArray.push(3);
        stackLinkedList.push(1);
        stackLinkedList.push(2);
        stackLinkedList.push(3);
        System.out.println(stackArray);
        System.out.println(stackLinkedList);

        System.out.printf("Find 2: %s\n", stackArray.search(2));
        System.out.printf("Find 2: %s\n", stackLinkedList.search(2));

        System.out.printf("POP: %s\n", stackArray.pop());
        System.out.printf("POP: %s\n", stackLinkedList.pop());

        stackArray.push(4);
        stackArray.push(5);
        stackLinkedList.push(4);
        stackLinkedList.push(5);
        System.out.println(stackArray);
        System.out.println(stackLinkedList);

        System.out.printf("PEEK: %s\n", stackArray.peek());
        System.out.printf("PEEK: %s\n", stackLinkedList.peek());

        System.out.printf("Find 3: %s\n", stackArray.search(3));
        System.out.printf("Find 3: %s\n", stackLinkedList.search(3));

        stackArray.push(2);
        stackLinkedList.push(2);
        System.out.printf("Find 2: %s\n", stackArray.search(2));
        System.out.printf("Find 2: %s\n", stackLinkedList.search(2));

        stackArray.push(6);
        stackLinkedList.push(6);
        System.out.println(stackArray);
        System.out.println(stackLinkedList);

        stackArray.clear();
        stackLinkedList.clear();
        System.out.println(stackArray);
        System.out.println(stackLinkedList);
    }

    private static void testLinkedList() {
        var linkedListUtils = new java.util.LinkedList<Integer>();
        var linkedList = new LinkedList<Integer>();
        var doublyLinkedList = new DoublyLinkedList<Integer>();

        linkedListUtils.addFirst(100);
        linkedList.addFirst(100);
        doublyLinkedList.addFirst(100);
        System.out.println("\n Adding 100 in the beginning");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.add(1, 111);
        linkedList.add(1, 111);
        doublyLinkedList.add(1, 111);
        System.out.println("\n Adding 111 at index 1");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.removeFirst();
        linkedList.removeFirst();
        doublyLinkedList.removeFirst();
        System.out.println("\nRemoving first");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("first my LinkedList:  " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.removeLast();
        linkedList.removeLast();
        doublyLinkedList.removeLast();
        System.out.println("\nRemoving last");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.add(1);
        linkedList.add(1);
        doublyLinkedList.add(1);
        System.out.println("\n Adding 1");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.add(3);
        linkedList.add(3);
        doublyLinkedList.add(3);
        System.out.println("\n Adding 3");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.removeLast();
        linkedList.removeLast();
        doublyLinkedList.removeLast();
        System.out.println("\nRemoving last");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.add(2);
        linkedList.add(2);
        doublyLinkedList.add(2);
        System.out.println("\n Adding 2");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.remove();
        linkedList.remove();
        doublyLinkedList.remove();
        System.out.println("\n Removing");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.add(4);
        linkedList.add(4);
        doublyLinkedList.add(4);
        System.out.println("\n Adding 4");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.add(2, 99);
        linkedList.add(2, 99);
        doublyLinkedList.add(2, 99);
        System.out.println("\n Adding 99 at index 2");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        System.out.println("\nContains 4?");
        System.out.println("java.util.LinkedList: " + linkedListUtils.contains(4));
        System.out.println("my LinkedList:        " + linkedList.contains(4));
        System.out.println("my doublyLinkedList:  " + doublyLinkedList.contains(4));

        linkedListUtils.remove(1);
        linkedList.removeAt(1);
        doublyLinkedList.removeAt(1);
        System.out.println("\n Removing at index 1");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        System.out.println("\nContains 4?");
        System.out.println("java.util.LinkedList: " + linkedListUtils.contains(4));
        System.out.println("my LinkedList:        " + linkedList.contains(4));
        System.out.println("my doublyLinkedList:  " + doublyLinkedList.contains(4));

        linkedListUtils.add(0, 77);
        linkedList.add(0, 77);
        doublyLinkedList.add(0, 77);
        System.out.println("\n Adding 77 at index 0");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        linkedListUtils.addLast(10);
        linkedList.addLast(10);
        doublyLinkedList.addLast(10);
        System.out.println("\n Adding 10");
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);

        System.out.println("\n my LinkedList:        " + linkedList);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.get(3));

        System.out.println("\n my doublyLinkedList:  " + doublyLinkedList);
        System.out.println(doublyLinkedList.get(0));
        System.out.println(doublyLinkedList.get(1));
        System.out.println(doublyLinkedList.get(2));
        System.out.println(doublyLinkedList.get(3));

        System.out.println("\nclearing");
        linkedListUtils.clear();
        linkedList.clear();
        doublyLinkedList.clear();
        System.out.println("java.util.LinkedList: " + linkedListUtils);
        System.out.println("my LinkedList:        " + linkedList);
        System.out.println("my doublyLinkedList:  " + doublyLinkedList);



    }

    private static void testArrayList() {
        var lista = new ArrayList<Integer>();
        for (var i = 0; i < 15; i++) {
            lista.add(i);
        }
        System.out.println(lista);
        System.out.println(lista.get(5));
        System.out.println(lista.getFirst());
        System.out.println(lista.getLast());
        System.out.printf("Contains 5? => %s%n", lista.contains(5));

        lista.removeAt(5);
        System.out.println(lista.get(5));
        lista.removeFirst();
        lista.removeLast();
        lista.remove(5);
        lista.remove(10);
        System.out.println(lista);
        System.out.printf("Contains 5? => %s%n", lista.contains(5));

    }

    private static void testPriorityQueue() {
        var queue = new PriorityQueue<Integer>(10);
        queue.add(2);
        queue.add(1);
        queue.add(5);
        queue.add(3);
        System.out.println("Q Size:  => " + queue.size());
        var size = queue.size();
        System.out.println(queue);
        System.out.println(queue.peek());
        var copy = queue.copy();
        for (int i = 0; i < size; i++) {
            System.out.println(i + " => " + queue.poll());
        }
        System.out.println(copy);
    }


    private static void testComparator() {
        var transactions = new java.util.ArrayList<Transaction>();
        Transaction transaction = new Transaction(LocalDate.now(), BigDecimal.valueOf(100), "A", "C");
        transactions.add(transaction);
        transactions.add(new Transaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(200), "B", "C"));
        transactions.add(new Transaction(LocalDate.now(), BigDecimal.valueOf(300), "A", "B"));
        transactions.add(new Transaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(400), "A", "C"));
        transactions.add(new Transaction(LocalDate.now().minusDays(1), BigDecimal.valueOf(100), "A", "C"));
        System.out.println(transactions);
        transactions.get(0);

        transactions.sort(Comparator.comparing(Transaction::date, Comparator.reverseOrder())
                .thenComparing(Transaction::origin)
                .thenComparing(Transaction::destination)
                .thenComparing(Transaction::value)
        );
        System.out.println(transactions);
        transactions.remove(0);
        transactions.remove(transaction);

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