package lab01;

import counter.ICounter;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class NonSaveIncrementer implements Runnable {
    private ICounter counter;

    public NonSaveIncrementer(ICounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Parameters.NUMBER_OF_ITERATIONS; i++) {
            counter.increment();
        }
    }
}
