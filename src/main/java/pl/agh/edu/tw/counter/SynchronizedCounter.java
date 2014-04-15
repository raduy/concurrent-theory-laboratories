package pl.agh.edu.tw.counter;

import pl.agh.edu.tw.lab02.BinarySemaphore;
import org.apache.log4j.Logger;


/**
 *  Counter synchronized with semaphores.
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SynchronizedCounter implements ICounter {
    private final static Logger LOGGER = Logger.getLogger(SynchronizedCounter.class.getCanonicalName());

    private int value;
    private BinarySemaphore semaphore = new BinarySemaphore();

    @Override
    public void increment() {
        try {
            semaphore.take();
            value++;
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            semaphore.release();
        }
    }

    public void decrement() {
        try {
            semaphore.take();
            value--;
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        return "SynchronizedCounter{" +
                "value=\t\t\t" + value +
                '}';
    }
}
