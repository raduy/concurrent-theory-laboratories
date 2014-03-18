package lab01;

import lab01.simple.SimpleThread;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class MainThreads {
    public static void main(String[] args) {
        final int NUMBER_OF_THREADS = Parameters.NUMBER_OF_THREADS;
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < threads.length; i++) {
            (threads[i] = new SimpleThread()).start();
        }
    }
}
