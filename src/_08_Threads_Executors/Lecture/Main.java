package _08_Threads_Executors.Lecture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SquareCalculator implements Callable<Integer> {
    private int number;

    public SquareCalculator(int number) {
        this.number = number;
    }

    public Integer call() throws Exception {
        Thread.sleep(1000); // simulate f.e. API call
        return (int) Math.pow(number, 2);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newSingleThreadExecutor();

        int number = 23;
        Future<Integer> future = exec.submit(new SquareCalculator(number));

        System.out.println("Calculating...");

        int result = future.get();

        System.out.println("Result = " + result);
        exec.shutdown();
    }
}