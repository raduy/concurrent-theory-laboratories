package lab02;

import lab01.NonSaveDecrementer;
import lab01.NonSaveIncrementer;
import lab01.Parameters;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Main {

    public static void startRun(int numberOfThreads, int numberOfIterations) {
        Parameters.NUMBER_OF_ITERATIONS = numberOfIterations;
        Parameters.NUMBER_OF_THREADS = numberOfThreads;

        final ICounter counter = new CounterWithSemaphore();

        System.out.println("" + numberOfThreads + " threads, " + Parameters.NUMBER_OF_ITERATIONS + " iterations.");
        Thread[] decrementers = new Thread[numberOfThreads];
        Thread[] incrementers = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            incrementers[i] = new Thread(new NonSaveIncrementer(counter));
            decrementers[i] = new Thread(new NonSaveDecrementer(counter));
        }

        for (int i = 0; i < numberOfThreads; i++) {
            incrementers[i].start();
            decrementers[i].start();
        }

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                decrementers[i].join();
                incrementers[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }

    public static void main(String[] args) {
        startRun(1, 30);
        startRun(1, 30000);
        startRun(20, 30);
        startRun(20, 3000);

    }
}
