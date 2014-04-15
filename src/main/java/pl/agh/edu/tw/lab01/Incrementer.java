package pl.agh.edu.tw.lab01;

import pl.agh.edu.tw.counter.*;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Incrementer implements Runnable {
    private ICounter counter;

    public Incrementer(ICounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Parameters.NUMBER_OF_ITERATIONS; i++) {
            counter.increment();
        }
    }
}
