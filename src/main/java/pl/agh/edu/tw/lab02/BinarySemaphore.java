package pl.agh.edu.tw.lab02;

/**
 * Simple Binary semaphore.
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class BinarySemaphore {
    private boolean signal = true; /* it's ready to take */

    public synchronized void take() throws InterruptedException {
        while (!signal) {
            this.wait();
        }
        signal = false;
    }

    public synchronized void release() {
        signal = true;
        this.notify();
    }
}