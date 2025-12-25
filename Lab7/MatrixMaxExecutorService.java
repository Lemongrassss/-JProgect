import java.util.concurrent.*;

public class MatrixMaxExecutorService {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[][] matrix = new int[100][100];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 10000);
            }
        }

        int threadCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int rowsPerThread = matrix.length / threadCount;

        Future<Integer>[] results = new Future[threadCount];

        for (int i = 0; i < threadCount; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i == threadCount - 1) ? matrix.length : startRow + rowsPerThread;
            results[i] = executor.submit(new MaxTask(matrix, startRow, endRow));
        }

        int globalMax = Integer.MIN_VALUE;
        for (Future<Integer> result : results) {
            globalMax = Math.max(globalMax, result.get());
        }

        executor.shutdown();
        System.out.println("Наибольший элемент в матрице: " + globalMax);
    }

    static class MaxTask implements Callable<Integer> {
        private final int[][] matrix;
        private final int startRow;
        private final int endRow;

        public MaxTask(int[][] matrix, int startRow, int endRow) {
            this.matrix = matrix;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public Integer call() {
            int max = Integer.MIN_VALUE;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] > max) {
                        max = matrix[i][j];
                    }
                }
            }
            return max;
        }
    }
}