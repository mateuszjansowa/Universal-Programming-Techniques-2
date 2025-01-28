package _08_Threads_Executors.Challenges;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task_2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            exec.execute(new Task(i));
        }

        exec.shutdown();
        try {
            if (!exec.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Nie wszystkie zadania zakonczyly sie");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Runnable {
    private final int id;
    private final Random random = new Random();

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Zadanie #%d zaczyna prace\n", id);
            Thread.sleep(random.nextInt(1000) + 100);
            System.out.printf("Zadanie #%d zakonczylo prace\n", id);
        } catch (InterruptedException e) {
            System.out.printf("Zadanie #%d zosta?o przerwane!\n", id);
            Thread.currentThread().interrupt();
        }
    }
}
