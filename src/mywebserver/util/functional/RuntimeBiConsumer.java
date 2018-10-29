package mywebserver.util.functional;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@FunctionalInterface
public interface RuntimeBiConsumer<T,U> extends BiConsumer<T,U> {

    void acceptEx(T t, U u) throws Exception;

    @Override
    default void accept(T t, U u) {
        try {
            acceptEx(t, u);
        }catch (Exception e){
            throw new RuntimeException("Inner: " + e.getMessage(), e);
        }
    }
}
