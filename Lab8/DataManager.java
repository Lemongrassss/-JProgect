import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataManager {
    private List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private List<String> processedData = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        try {
            Path filePath = Paths.get(source);
            data = Files.readAllLines(filePath);
            System.out.println("Данные загружены из файла: " + source);
            
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            data = Arrays.asList("apple", "banana", "cherry");
        }
    }

    public void processData() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<List<String>>> tasks = new ArrayList<>();

        for (Object processor : processors) {
            tasks.add(() -> {
                return Arrays.stream(processor.getClass().getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(DataProcessor.class))
                    .flatMap(method -> {
                        try {
                            return ((List<String>) method.invoke(processor, data)).stream();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                    })
                    .collect(Collectors.toList());
            });
        }

        try {
            List<Future<List<String>>> results = executor.invokeAll(tasks);
            for (Future<List<String>> future : results) {
                processedData.addAll(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Данные обработаны.");
    }

    public void saveData(String destination) {
        try {
            Path filePath = Paths.get(destination);
            Files.write(filePath, processedData);
            System.out.println("Результаты сохранены в файл: " + destination);
            
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public List<String> getProcessedData() {
        return processedData;
    }
}