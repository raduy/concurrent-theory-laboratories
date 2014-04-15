package pl.agh.edu.tw.lab01;

import pl.agh.edu.tw.counter.*;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Decrementer implements Runnable {
    private ICounter counter;

    public Decrementer(ICounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Parameters.NUMBER_OF_ITERATIONS; i++) {
            counter.decrement();
        }
    }
}
