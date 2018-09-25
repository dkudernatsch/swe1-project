package mywebserver.util.parser;

public interface Parser<T> {

    ParseResult<T> parse(CharSequence input);

}
