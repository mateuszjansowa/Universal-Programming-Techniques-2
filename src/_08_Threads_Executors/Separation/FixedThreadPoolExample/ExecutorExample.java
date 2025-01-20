package _08_Threads_Executors.Separation.FixedThreadPoolExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 4; i++) {
            System.out.println(name + " " + i);
        }
        try {
            Thread.sleep(1000); // symulacja pracy
        } catch (InterruptedException e) {
            System.out.printf("%s przerwany", name);
        }
    }
}

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 10; i++) {
            exec.execute(new Task("Task " + i));
        }

        exec.shutdown();
    }
}
