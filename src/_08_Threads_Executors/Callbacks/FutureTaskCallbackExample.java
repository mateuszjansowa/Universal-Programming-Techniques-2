package _08_Threads_Executors.Callbacks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class CallbackTask extends FutureTask<Integer> {
    public CallbackTask(Callable<Integer> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        try {
            if (isCancelled()) {
                System.out.println("Zadanie zosta?o anulowane");
            } else {
                System.out.println("Wynik zadania: " + get());
            }
        } catch (Exception e) {
            System.out.println("Wyst?pi? wyj?tek: " + e.getMessage());
        }
    }
}

public class FutureTaskCallbackExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CallbackTask task = new CallbackTask(() -> {
            Thread.sleep(2000);
            return 1996;
        });

        executor.execute(task);
        executor.shutdown();
    }
}