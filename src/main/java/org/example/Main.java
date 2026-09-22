package org.example;

import org.example.hashmap.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        testHashMap();


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