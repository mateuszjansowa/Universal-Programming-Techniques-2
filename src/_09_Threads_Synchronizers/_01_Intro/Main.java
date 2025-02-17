package _09_Threads_Synchronizers._01_Intro;

class Balance {
    private int number = 0;

    public int balance() {
        number++; // operacja nieatomowa - moze prowadzic do bledow
        number--;
        return number;
    }
}

public class Main {
    public static void main(String[] args) {
        Balance balance = new Balance();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                if (balance.balance() != 0) {
                    System.out.println("Blad!");
                    break;
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
