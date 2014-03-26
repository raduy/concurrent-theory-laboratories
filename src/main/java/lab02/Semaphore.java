package lab02;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Semaphore {
    private final int size;
    private int actualSize;

    public Semaphore(int size) {
        this.size = size;
        this.actualSize = 0;
    }

    public synchronized void up() {
        actualSize = (actualSize + 1) % (size + 1);
        this.notify();
    }

    public synchronized void down() throws InterruptedException {
        while (!(actualSize < size)) {
            this.wait();
        }
        actualSize = actualSize > 0 ? actualSize - 1 : 0;
    }
}