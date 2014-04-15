package pl.agh.edu.tw.lab02;

import pl.agh.edu.tw.lab01.*;
import pl.agh.edu.tw.counter.*;
import org.apache.log4j.Logger;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Run {
    private final static Logger LOGGER = Logger.getLogger(Run.class.getCanonicalName());

    public static void startRun(final ICounter counter, int numberOfThreads, int numberOfIterations) {
        Parameters.NUMBER_OF_ITERATIONS = numberOfIterations;
        Parameters.NUMBER_OF_THREADS = numberOfThreads;

        LOGGER.info("" + numberOfThreads + " threads, " + Parameters.NUMBER_OF_ITERATIONS + " iterations.");

        Thread[] decrementers = new Thread[numberOfThreads];
        Thread[] incrementers = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            incrementers[i] = new Thread(new Incrementer(counter));
            decrementers[i] = new Thread(new Decrementer(counter));
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

        LOGGER.info(counter);
    }

    public static void main(String[] args) {

        final ICounter nonThreadSaveCounter = new NonThreadSaveCounter();

        LOGGER.info("------------ NON THREAD SAVE COUNTER ----------------");
        startRun(nonThreadSaveCounter, 1, 30);
        startRun(nonThreadSaveCounter, 1, 30000);
        startRun(nonThreadSaveCounter, 20, 30);
        startRun(nonThreadSaveCounter, 20, 3000);

        final ICounter synchronizedCounter = new SynchronizedCounter();

        LOGGER.info("------------ THREAD SAVE COUNTER --------------------");
        startRun(synchronizedCounter, 1, 30);
        startRun(synchronizedCounter, 1, 30000);
        startRun(synchronizedCounter, 20, 30);
        startRun(synchronizedCounter, 20, 3000);
    }
}
