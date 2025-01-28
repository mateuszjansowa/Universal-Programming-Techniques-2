package _08_Threads_Executors.Challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task_3 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(5);

        // submit Futures
        for (int i = 1; i <= 5; i++) {
            int number = random.nextInt(30);
            Future<Integer> future = exec.submit(new CubeNumber(number));
            futures.add(future);
            System.out.printf("Submitted CubeNumber #%d...\n", i);
        }

        // read results
        for (int i = 0; i < 5; i++) {
            try {
                int result = futures.get(i).get();
                System.out.printf("CubeNumber #%d result: %d\n\n", i, result);
            } catch (Exception e) {
                System.out.printf("Issue in executing CubeNumber #%d", i);
            }
        }

        exec.shutdown();
    }
}

class CubeNumber implements Callable<Integer> {
    private final int number;

    public CubeNumber(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000); // symulacja dlugiej pracy
        return (int) Math.pow(number, 3);
    }
}