package lab02.diningphilosophers;

import lab02.BinarySemaphore;
import lab02.Semaphore;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Dinner {
    public static void main(String[] args) {
        final BinarySemaphore[] forks = new BinarySemaphore[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new BinarySemaphore();
        }

        final Semaphore butler = new Semaphore(4);

        Thread[] philosophers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Thread(new Philosopher(i, forks, butler));
        }

        for (Thread philosopher : philosophers) {
            philosopher.start();
        }

    }
}
