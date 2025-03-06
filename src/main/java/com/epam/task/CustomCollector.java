package com.epam.task;

import java.util.TreeMap;

/**
 * Build a Custom Collector Create a custom collector that accumulates elements 
 * into a TreeMap instead of a HashMap. Demonstrate its usage with a sample program.
 */
public class CustomCollector {

    public static void main(String args[]) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "Five");
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(4, "Four"); 
        treeMap.put(2, "Two");
        System.out.println("TreeMap: " + treeMap);
        
        assert treeMap.firstKey() == 1;
        assert treeMap.lastKey() == 5;
    }
}
