package _08_Threads_Executors.CallableAndFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SquareCalculator implements Callable<Integer> {
    private int number;

    public SquareCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000); // symulacja dlugiej operacji
        return (int) Math.pow(number, number);
    }
}

public class CallableExample {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        int number = 22;
        Future<Integer> future = executor.submit(new SquareCalculator(number));

        System.out.println("Calculating...");
        int result = future.get();

        System.out.println("Result of " + number + "^" + number + " = " + result);
        executor.shutdown();
    }
}
