package org.example;

import org.example.hashmap.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        var map = new HashMap<String, Integer>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 1);
        map.put("A", 3);
        System.out.println(map);

        var hashmap = new java.util.HashMap<String, Integer>();
        hashmap.put("A", 1);
        hashmap.put("B", 2);
        hashmap.put("C", 1);
        System.out.println(hashmap);

    }
}