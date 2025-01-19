package _08_Threads_Executors.ThreadsIntro;

// via Thread
class Timer extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running...");
    }
}

// via Runnable
class Task implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Runnable thread is running...");
        }
        System.out.println("Stopping! Thread interrupted.");
    }
}

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.start();

        Thread task = new Thread(new Task());
        task.start();
        task.interrupt();
    }
}
