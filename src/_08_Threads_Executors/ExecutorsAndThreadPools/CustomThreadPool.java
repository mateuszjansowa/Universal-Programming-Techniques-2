package _08_Threads_Executors.ExecutorsAndThreadPools;

import java.util.concurrent.*;

public class CustomThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // corePoolSize
                5, // maximumPoolSize
                60, TimeUnit.SECONDS, // keepAliveTime
                new LinkedBlockingQueue<>()
        );

        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.execute(() -> System.out.println("Zadanie " + taskId + " wykonane przez " + Thread.currentThread().getName()));
        }

        executor.shutdown();
    }
}
