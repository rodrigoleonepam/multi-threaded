# Project Multi-Threaded

### Author: Rodrigo León

### Tasks:
* Multi-threaded Word Count Write a program that reads a large text file and counts the frequency of each word using multiple threads and Java 8 features like CompletableFuture.
* LRU Cache Implementation Implement an LRU (Least Recently Used) Cache using Java 8's LinkedHashMap to store key-value pairs.
* Implement a Thread-safe Singleton Pattern Implement a Singleton pattern in Java that is thread-safe and uses Java 8 features.
* Concurrent Processing of Large Data Set Write a program that processes a large data set (e.g., a list of transactions) concurrently using Java 8's parallelStream.
* Build a Custom Collector Create a custom collector that accumulates elements into a TreeMap instead of a HashMap. Demonstrate its usage with a sample program.

### Solutions

1. WordCount
```bash
mvn exec:java -Dexec.mainClass="com.epam.task.WordCount"
```
2. LRUCache
```bash
mvn exec:java -Dexec.mainClass="com.epam.task.LRUCache"
```
3. SingletonPattern
```bash
mvn exec:java -Dexec.mainClass="com.epam.task.SingletonPattern"
```
4. ParallelStream
```bash
mvn exec:java -Dexec.mainClass="com.epam.task.ParallelStream"
```
5. CustomCollector
```bash
mvn exec:java -Dexec.mainClass="com.epam.task.CustomCollector"
```
