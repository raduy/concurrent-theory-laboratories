package pl.agh.edu.tw.lab03;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SynchronousConsumer implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(SynchronousConsumer.class.getCanonicalName());
    private static int ID = 1;
    private final BufferQueue<Integer> buffer;
    private final Random random = new Random();
    private final int sleepTimeInMs;
    private final int consTimeInMs;
    private final int id = ID++;

    public SynchronousConsumer(BufferQueue<Integer> buffer, int sleepTimeInMs, int consTimeInMs) {
        this.buffer = buffer;
        this.sleepTimeInMs = sleepTimeInMs;
        this.consTimeInMs = consTimeInMs;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                doConsume();
                TimeUnit.MILLISECONDS.sleep(sleepTimeInMs);
            }
        } catch (InterruptedException ex) {
            LOGGER.info("Consumer " + id + " has been interrupted");
        }
    }

    private void doConsume() throws InterruptedException {
        final List<Integer> resource = buffer.consume(random.nextInt(buffer.getMaxSize() / 2) + 1);
        LOGGER.info("Consumer " + id + " retrieved resource " + resource + " to consume");
        TimeUnit.MILLISECONDS.sleep(consTimeInMs);
    }
}
