package _08_Threads_Executors.CallableAndFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit(() -> {
            throw new RuntimeException("Error while executing thread!");
        });

        try {
            int result = future.get();
        } catch (ExecutionException e) {
            System.out.println("Thread threw exception: " + e.getCause());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
