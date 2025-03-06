package com.epam.task;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsynchronousFileProcessing {

    private final ExecutorService executor;

    public AsynchronousFileProcessing(ExecutorService executor) {
        this.executor = executor;
    }

    private long countWordsInFile(Path file) {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !word.isEmpty())
                    .count();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + file.getFileName(), e);
        }
    }

    private CompletableFuture<Long> processFileAsync(Path file) {
        return CompletableFuture.supplyAsync(() -> countWordsInFile(file), executor)
            .exceptionally(ex -> {
                System.err.println("Error processing " + file.getFileName() + ": " + ex.getMessage());
                return 0L;
            });
    }

    public CompletableFuture<Long> processFiles(List<Path> files) {
        List<CompletableFuture<Long>> futures = files.stream()
            .map(this::processFileAsync)
            .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                    .map(CompletableFuture::join)
                    .reduce(0L, Long::sum));
    }

    public void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate.");
                }
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }



}
