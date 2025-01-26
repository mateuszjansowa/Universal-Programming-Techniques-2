package _08_Threads_Executors.ExecutorsAndThreadPools;

import java.util.concurrent.*;
import java.util.*;

public class InvokeAnyExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = Arrays.asList(
                () -> { Thread.sleep(3000); return 3; },
                () -> { Thread.sleep(1000); return 1; },
                () -> { Thread.sleep(2000); return 2; }
        );

        System.out.println("Uruchamianie zada?...");
        int result = executor.invokeAny(tasks); // Zwraca pierwszy uko?czony wynik

        System.out.println("Pierwszy uko?czony wynik: " + result);
        executor.shutdown();
    }
}
