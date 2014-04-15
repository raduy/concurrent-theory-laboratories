package pl.agh.edu.tw.lab01;

import pl.agh.edu.tw.counter.*;
import org.apache.log4j.Logger;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Run {
    private final static Logger LOGGER = Logger.getLogger(Run.class.getCanonicalName());
   
    public static void startRun(int numberOfThreads, int numberOfIterations) {

        Parameters.NUMBER_OF_ITERATIONS = numberOfIterations;
        Parameters.NUMBER_OF_THREADS = numberOfThreads;

        final NonThreadSaveCounter counter = new NonThreadSaveCounter(0);

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

        System.out.println(counter);
    }

    public static void main(String[] args) {
        startRun(1, 30);
        startRun(1, 300000);
        startRun(200, 30);
        startRun(200, 300);
    }
}
