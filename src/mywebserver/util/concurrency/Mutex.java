package mywebserver.util.concurrency;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;

public class Mutex<T> {

    public Mutex(T t){
        this.t = t;
        lock = new ReentrantLock();
    }

    private ReentrantLock lock;
    private T t;

    MutexValue<T> get(){
        lock.lock();
        return new MutexValue<>(t, this);
    }

    private T getVal(){
        return t;
    }
    private void setVal(T t){
        this.t = t;
    }

    public static class MutexValue<T> implements AutoCloseable {

        private Mutex<T> lock;

        MutexValue(T t, Mutex<T> lock){
            this.lock = lock;
        }

        public <R> R with(Function<T, R> fn) {
            return fn.apply(lock.getVal());
        }

        public void with(Consumer<T> fn) {
            fn.accept(lock.getVal());
        }

        public void mutate(Function<T,T> fn) {
            lock.setVal(fn.apply(lock.getVal()));
        }

        @Override
        public void close() {
            lock.lock.unlock();
        }

    }



}
