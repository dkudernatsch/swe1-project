package mywebserver.util.functional;

import java.util.function.Consumer;

public interface RuntimeConsumer<T> extends Consumer<T> {

    void acceptEx(T t) throws Exception;

    @Override
    default void accept(T t) {
        try {
            acceptEx(t);
        }catch (Exception e){
            throw new RuntimeException("Inner: " + e.getMessage(), e);
        }
    }
}
