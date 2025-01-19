package _08_Threads_Executors.Separation.Executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor(); // pula wątków - jeden wątek obsługuje wszystkie zadania w kolejności
        executor.execute(() -> System.out.println("Task in thread finished!"));
    }
}
