package mywebserver.util.parser;

import java.util.function.Function;

public class ParseResult<T> {

    private T matched;
    private boolean successful;
    private CharSequence remaining;

    public ParseResult(T matched, CharSequence remaining, boolean successful) {
        this.matched = matched;
        this.remaining = remaining;
        this.successful = successful;
    }

    public boolean success() {
        return successful;
    }

    public T getMatched() {
        return matched;
    }

    public CharSequence getRemaining() {
        return remaining;
    }

    public static <T, R> ParseResult<R> map(ParseResult<T> result, Function<T, R> fn) {
        if (result.success()) {
            return new ParseResult<>(fn.apply(result.matched), result.getRemaining(), true);
        } else {
            return new ParseResult<>(null, result.getRemaining(), false);
        }
    }

}
