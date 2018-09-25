package mywebserver.util.parser;

public class ParseResult<T> {

    private T matched;
    private boolean successful;
    private CharSequence remaining;

    public ParseResult(T matched, CharSequence remaining, boolean successful){
        this.matched = matched;
        this.remaining = remaining;
        this.successful = successful;
    }

    public boolean success(){
        return successful;
    }

    public T getMatched(){
        return matched;
    }

    public CharSequence getRemaining(){
        return remaining;
    }

}
