package _08_Threads_Executors.CallableAndFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(3000);
            return 42;
        });

        System.out.println("Czy zadanie zakonczone? " + future.isDone());

        Thread.sleep(1000);
        System.out.println("Czy zadanie zakonczone? " + future.isDone());

        future.cancel(true); // Anulujemy zadanie

        System.out.println("Czy zadanie anulowane? " + future.isCancelled());
        executor.shutdown();
    }
}
