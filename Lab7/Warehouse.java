import java.util.*;
import java.util.concurrent.*;

public class Warehouse {
    private static final int MAX_WEIGHT_PER_TRIP = 150;
    private static BlockingQueue<Integer> goodsQueue = new LinkedBlockingQueue<>();
    private static volatile int currentWeight = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            goodsQueue.add(rand.nextInt(50) + 1);
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            futures.add(executor.submit(new Loader("Грузчик-" + (i + 1))));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Loader implements Runnable {
        private final String name;

        public Loader(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (goodsQueue.isEmpty()) {
                        System.out.println(name + ": товаров больше нет, завершаю работу.");
                        break;
                    }
                    if (currentWeight >= MAX_WEIGHT_PER_TRIP) {
                        System.out.println(name + ": набрано " + currentWeight + " кг, отправляюсь на другой склад.");
                        currentWeight = 0;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                }

                Integer weight = goodsQueue.poll();
                if (weight == null) break;

                synchronized (lock) {
                    currentWeight += weight;
                    System.out.println(name + " взял товар весом " + weight + " кг. Текущий вес: " + currentWeight + " кг.");
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}