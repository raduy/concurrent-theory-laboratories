package lab01;

import lab01.counter.ICounter;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class NonSaveDecrementer implements Runnable {
    private ICounter counter;

    public NonSaveDecrementer(ICounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Parameters.NUMBER_OF_ITERATIONS; i++) {
            counter.decrement();
        }
    }
}
