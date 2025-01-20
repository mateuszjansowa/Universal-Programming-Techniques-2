package _08_Threads_Executors.Separation.AwaitTerminationExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AwaitTerminationExample {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        exec.execute(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            System.out.println("Zadanie 1 zakończone");
        });

        exec.execute(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("Zadanie 2 zakończone");
        });

        exec.shutdown();

        try {
            if (!exec.awaitTermination(3, TimeUnit.SECONDS)) {
                System.out.println("Wątki nadal pracują. Zamykam...");
                exec.shutdownNow();
            }
        } catch (InterruptedException e) {
            exec.shutdownNow();
        }
    }
}
