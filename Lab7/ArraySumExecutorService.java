import java.util.concurrent.*;

public class ArraySumExecutorService {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        int threadCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int chunkSize = array.length / threadCount;

        Future<Integer>[] results = new Future[threadCount];

        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1) ? array.length : start + chunkSize;
            results[i] = executor.submit(new SumTask(array, start, end));
        }

        int totalSum = 0;
        for (Future<Integer> result : results) {
            totalSum += result.get();
        }

        executor.shutdown();
        System.out.println("Сумма элементов массива: " + totalSum);
    }

    static class SumTask implements Callable<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
    }
}