package _09_Threads_Synchronizers.Challenges;

class SharedResource {
    private int data = 0;

    public void update(int value) {
        synchronized (this) {
            int temp = data;
            temp += value;
            data = temp;
        }
    }

    public int getData() {
        return data;
    }
}

public class Task_02 {
    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                resource.update(1);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted!");
        }

        System.out.println("Oczekiwany wynik: 2000, Aktualny wynik: " + resource.getData());
    }
}
