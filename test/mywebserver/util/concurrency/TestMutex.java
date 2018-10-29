package mywebserver.util.concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class TestMutex {

    @Test
    public void testSingleThread() {
        Mutex<Integer> intLock = new Mutex<>(1);
        try (Mutex.MutexValue<Integer> i = intLock.get()) {
            i.with((Consumer<Integer>) (ival) -> Assert.assertEquals(new Integer(1), ival));
        }
        try (Mutex.MutexValue<Integer> i = intLock.get()) {
            i.with((Consumer<Integer>) (ival) -> Assert.assertEquals(new Integer(1), ival));
        }
    }

    @Test
    public void allTheThreads() throws Exception {
        Mutex<Integer> intLock = new Mutex<>(1);
        ExecutorService tpe = Executors.newFixedThreadPool(100);
        tpe.invokeAll(
                IntStream.range(0, 10)
                        .map( (Callable<Integer>) (i) -> i )
        );

        tpe.awaitTermination(10, TimeUnit.SECONDS);
        try (Mutex.MutexValue<Integer> i = intLock.get()) {
            i.with((Consumer<Integer>) (ival) -> Assert.assertEquals(new Integer(1001), ival));
        }

    }
}
