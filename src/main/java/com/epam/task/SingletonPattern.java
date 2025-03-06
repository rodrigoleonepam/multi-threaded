package com.epam.task;

/**
 * Implement a Thread-safe Singleton Pattern Implement a Singleton pattern in Java 
 * that is thread-safe and uses Java 8 features.
 */
public class SingletonPattern {
    
    private static final SingletonPattern instance = new SingletonPattern();

    public static SingletonPattern getInstance() {
        return instance;
    }

    public void printMessage() {
        System.out.println("Hello, Singleton Pattern!");
    }

    public static void main(String[] args) {
        SingletonPattern singleton = SingletonPattern.getInstance();
        singleton.printMessage();
    }
}
