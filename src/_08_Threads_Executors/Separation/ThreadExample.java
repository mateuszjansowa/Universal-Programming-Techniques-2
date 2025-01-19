package _08_Threads_Executors.Separation;

public class ThreadExample {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Thread is running...");
        Thread thread = new Thread(task);
        thread.start();
    }
}
