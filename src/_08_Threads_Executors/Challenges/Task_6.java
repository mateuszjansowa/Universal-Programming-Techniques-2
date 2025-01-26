package _08_Threads_Executors.Challenges;

// 6. Obs?uga anulowania zada? (Future.cancel)
//Cel: Masz 10 zada?, które wykonuj? kosztown? operacj? (Thread.sleep(3000)).
//Program powinien:
//
//Uruchomi? wszystkie zadania w ExecutorService.
//Po 2 sekundach anulowa? wszystkie niezako?czone zadania (cancel(true)).
//Wypisa?, które zadania zosta?y zako?czone, a które anulowane.
//? Wskazówki:
//
//U?yj Future.cancel(true), aby spróbowa? zatrzyma? zadania.
//Obs?u? wyj?tek InterruptedException w Callable.
//Sprawd? future.isCancelled() i future.isDone().
public class Task_6 {
}
