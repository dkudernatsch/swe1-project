package mywebserver.util.concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
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
            IntStream.range(0,1000).mapToObj( __ ->

                    (Callable<Integer>) () -> {
                        try(Mutex.MutexValue<Integer> i = intLock.get()){
                            i.mutate((is) -> is+1);
                            return i.with(Function.identity());
                        }

                     }
            ).collect(Collectors.toList())
        );

        try (Mutex.MutexValue<Integer> i = intLock.get()) {
            i.with((Consumer<Integer>) (ival) -> Assert.assertEquals(new Integer(1001), ival));
        }

    }
}
