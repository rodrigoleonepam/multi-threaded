package com.epam.task;

/**
 * Concurrent Processing of Large Data Set Write a program that processes a 
 * large data set (e.g., a list of transactions) concurrently using Java 8's parallelStream.
 */
public class ParallelStream {

    public static void main(String[] args) {
        // Create a large data set
        int[] data = new int[1000000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        // Process the data concurrently using parallelStream
        long startTime = System.currentTimeMillis();
        int sum = java.util.Arrays.stream(data).parallel().sum();
        long endTime = System.currentTimeMillis();

        System.out.println("Sum: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

}
