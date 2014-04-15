package pl.agh.edu.tw.lab02;

/**
 * Counting semaphore.
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Semaphore {
    private int value;
    private int waitingThreads = 0; /* explicit set to zero for clarity */

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void take() throws InterruptedException {
        if (value > 0) {
            value--;
        } else {
            waitingThreads++;
            while(value <= 0) {
                this.wait();
            }
            waitingThreads--;
            value--;
        }
    }

    public synchronized void release() throws InterruptedException {
        if (waitingThreads > 0) {
            this.notify();
        } else {
            ++value;
        }
    }
}
