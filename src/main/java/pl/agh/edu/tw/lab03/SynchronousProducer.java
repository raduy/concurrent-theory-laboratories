package pl.agh.edu.tw.lab03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SynchronousProducer implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(SynchronousProducer.class);
    private static int ID = 1;
    private final BufferQueue<Integer> buffer;
    private final Random random = new Random();
    private final int sleepTimeInMs;
    private final int prodTimeInMs;
    private final int id = ID++;

    public SynchronousProducer(BufferQueue<Integer> buffer, int sleepTimeInMs, int prodTimeInMs) {
        this.buffer = buffer;
        this.sleepTimeInMs = sleepTimeInMs;
        this.prodTimeInMs = prodTimeInMs;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                doProduce();
                TimeUnit.MILLISECONDS.sleep(sleepTimeInMs);
            }
        } catch (InterruptedException ex) {
            LOG.info("Producer {} has been interrupted", id);
        }
    }

    private void doProduce() throws InterruptedException {
        List<Integer> chunk = new ArrayList<>();
        int chunkSize = random.nextInt(buffer.getMaxSize() / 2) + 1;
        while (chunkSize > 0) {
            chunk.add(random.nextInt(100));
            chunkSize--;
        }
        buffer.produce(chunk);
        LOG.info("Producer {} produced resource {}", id, chunk);
        TimeUnit.MILLISECONDS.sleep(prodTimeInMs);
    }
}
