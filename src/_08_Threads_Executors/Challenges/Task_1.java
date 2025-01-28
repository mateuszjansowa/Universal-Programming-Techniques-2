package _08_Threads_Executors.Challenges;

import java.util.Random;

// 1. Uruchomienie prostych w?tków
//Cel: Uruchom 3 w?tki, które wypisz? na ekran tekst "W?tek X dzia?a" 10 razy, gdzie X to numer w?tku.
//
//? Wskazówki:
//
//Mo?esz u?y? Thread lub Runnable.
//Ka?dy w?tek powinien dzia?a? w osobnej klasie.
//W?tek powinien losowo usypia? si? na 100-500 ms mi?dzy iteracjami (Thread.sleep).
public class Task_1 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }
    }
}

class MyRunnable implements Runnable {
    private int id;
    private static final Random random = new Random();

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int sleepTime = random.nextInt(500) + 100;
            try {
                Thread.sleep(sleepTime);
                System.out.printf("Watek #%d dziala (iteracja %d)\n", id, i + 1);
            } catch (InterruptedException e) {
                System.out.printf("Watek #%d zostal przerwany (iteracja %d)\n", id, i + 1);
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
