package _08_Threads_Executors.ExecutorsAndThreadPools;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = Arrays.asList(
                () -> {
                    Thread.sleep(1000);
                    return 1;
                },
                () -> {
                    Thread.sleep(2000);
                    return 2;
                },
                () -> {
                    Thread.sleep(3000);
                    return 3;
                }
        );

        System.out.println("Running on tasks...");
        List<Future<Integer>> results = executor.invokeAll(tasks);

        for (Future<Integer> result : results) {
            try {
                System.out.println("Result: " + result.get()); // blokada do momentu zakonczenia wszystkich zadan
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        executor.shutdown();
    }
}
