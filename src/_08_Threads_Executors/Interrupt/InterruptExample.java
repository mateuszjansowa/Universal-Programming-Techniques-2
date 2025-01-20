package _08_Threads_Executors.Interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class InterruptibleTask implements Runnable {
    private String name;

    public InterruptibleTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(name + " is interrupted!");
                return;
            }
            System.out.println(name);
            Thread.yield(); // oddaje czas procesora
        }
    }
}

public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 2; i++) {
            exec.execute(new InterruptibleTask("Task " + i));
        }

        Thread.sleep(1000);
        exec.shutdownNow(); // ustawienie flagi isInterrupted
    }
}
