package lab02.diningphilosophers;

import lab02.BinarySemaphore;
import lab02.Semaphore;

import java.util.Random;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Philosopher implements Runnable {
    private final static Random random = new Random();
    private final int id;
    private final BinarySemaphore[] forks;
    private final Semaphore butler;

    public Philosopher(int id, BinarySemaphore[] forks, Semaphore butler) {
        this.id = id;
        this.forks = forks;
        this.butler = butler;
    }

    private synchronized void think() throws InterruptedException {
        System.out.println("I'm " + id + "th philosopher and I'm thinking now.");
        wait(random.nextInt(5000));
    }

    private synchronized void eat() throws InterruptedException {
        butler.down();
        forks[id].down();
        forks[(id + 1) % 5].down();
        System.out.println("I'm " + id + "th and I'm going to eat sth.");
        forks[id].up();
        forks[(id + 1) % 5].up();
        butler.up();

        wait(random.nextInt(5000));
    }


    @Override
    public void run() {
        while (true) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
