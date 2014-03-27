package lab02;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class BinarySemaphore {
    private boolean signal = true; //its up

    public synchronized void up() {
        signal = true;
        this.notify();
    }

    public synchronized void down() throws InterruptedException {

        while (!signal) {
            this.wait();
        }
        signal = false;
    }
}