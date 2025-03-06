package com.epam.task;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Multi-threaded Word Count Write a program that reads a large text file and counts 
 * the frequency of each word using multiple threads and Java 8 features like CompletableFuture.
 */
public class WordCount {
    
    public static void main(String args[]) {
        File file = new File("sherlock_holmes.txt");
        //long wordCount = countWordsInFile(file.toPath());
        //System.out.println("Word count: " + wordCount);
        int lineCount = countLinesInFile(file.toPath());
        System.out.println("Line count: " + lineCount);

        // Separates the original file into 2 parts
        int parts = 2;
        createBlockFiles(file.toPath(), lineCount / parts);

        System.out.println("Creating executor service");
        ExecutorService executor = Executors.newFixedThreadPool(parts);
        AsynchronousFileProcessing asyncProcessor = new AsynchronousFileProcessing(executor);

        File file0 = new File("file0.txt");
        File file1 = new File("file1.txt");
        List<Path> files = List.of(file0.toPath(), file1.toPath());

        System.out.println("Starting processing");
        CompletableFuture<Long> totalWordCountFuture = asyncProcessor.processFiles(files);
        totalWordCountFuture.thenAccept(wordCount -> {
            System.out.println("Total word count: " + wordCount);
            executor.shutdown();
        });

        System.out.println("Processing completed");
    }

    private static int countLinesInFile(Path file) {
        try (Stream<String> lines = Files.lines(file)) {
            return (int) lines.count();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + file.getFileName(), e);
        }
    }

    private static void createBlockFiles(Path file, int blockSize) {
        int index = 0;
        try (Stream<String> lines = Files.lines(file)) {
            List<String> lineList = lines.collect(Collectors.toList());
            for (int i = 0; i < lineList.size(); i += blockSize) {
                List<String> block = lineList.subList(i, Math.min(i + blockSize, lineList.size()));
                Path blockFile = file.resolveSibling("file" + index++ + ".txt");
                Files.write(blockFile, block);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + file.getFileName(), e);
        }
    }
}
