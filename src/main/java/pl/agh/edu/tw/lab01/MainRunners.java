package pl.agh.edu.tw.lab01;

import pl.agh.edu.tw.lab01.simple.SimpleRunner;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class MainRunners {

    public static void main(String[] args) {
        final int NUMBER_OF_THREADS = 10;
        Thread[] threads = new Thread[NUMBER_OF_THREADS];
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new SimpleRunner());
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
