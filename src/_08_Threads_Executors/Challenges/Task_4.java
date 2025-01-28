package _08_Threads_Executors.Challenges;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task_4 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] numbers = new int[1_000_000];
        int totalSum = 0;
        ExecutorService exec = Executors.newFixedThreadPool(4);

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(10);
        }

        List<Callable<Integer>> tasks = List.of(
                new Summator(numbers, 0, numbers.length / 4),
                new Summator(numbers, numbers.length / 4, numbers.length / 2),
                new Summator(numbers, numbers.length / 2, (numbers.length - numbers.length / 4)),
                new Summator(numbers, (numbers.length - numbers.length / 4), numbers.length)
        );

        try {
            List<Future<Integer>> results = exec.invokeAll(tasks);
            for (Future<Integer> result : results) {
                int partialSum = result.get();
                System.out.printf("Result is %d\n", partialSum);
                totalSum += partialSum;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Execution was interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            exec.shutdown();
        }

        System.out.printf("Total sum: %d\n", totalSum);
    }
}

class Summator implements Callable<Integer> {
    private final int[] arr;
    private final int start, end;

    public Summator(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        if (Thread.currentThread().isInterrupted()) {
            return 0;
        }

        return Arrays.stream(arr, start, end).sum();
    }
}
