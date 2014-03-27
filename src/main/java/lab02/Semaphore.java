package lab02;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Semaphore {
    private final int size;
    private int actualSize;

    public Semaphore(int size) {
        this.size = size;
        this.actualSize = size;
    }

    public synchronized void up() {
        actualSize = actualSize + 1;
        this.notify();
    }

    public synchronized void down() throws InterruptedException {
        while (!(actualSize > 0)) {
            this.wait();
        }
        actualSize = actualSize - 1;
    }
}

//h/t
//monitory z czerwonej ksiazki
//przemyslec sposob implementacji w Java
//czemu monitor z Javy to nie klasyczny monitor