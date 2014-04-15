package lab02.diningphilosophers;

import lab02.BinarySemaphore;
import lab02.Semaphore;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Class represents philosopher for five dining philosophers problem.
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Philosopher implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Philosopher.class.getCanonicalName());

    private final static Random random = new Random();
    private static int nextID = 1;
    private final int id;
    private final BinarySemaphore[] forks;
    private final Semaphore butler;

    public Philosopher(BinarySemaphore[] forks, Semaphore butler) {
        this.id = nextID++;
        this.forks = forks;
        this.butler = butler;
    }

    private synchronized void think() throws InterruptedException {
        LOGGER.info("I'm " + id + "th philosopher and I'm thinking now.");

        TimeUnit.SECONDS.sleep(random.nextInt(5));
    }

    private synchronized void eat() throws InterruptedException {
        butler.take();
        forks[id % 5].take();
        forks[(id + 1) % 5].take();

        /* tabs for better readability */

        LOGGER.info("\t\t\t\t\t\t\t I'm " + id + "th and I'm going to eat sth.");
        TimeUnit.SECONDS.sleep(random.nextInt(5));

        forks[id % 5].release();
        forks[(id + 1) % 5].release();
        butler.release();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                eat();
                think();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
