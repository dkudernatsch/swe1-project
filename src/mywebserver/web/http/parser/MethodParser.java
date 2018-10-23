package mywebserver.web.http.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.util.parser.SimpleRegexParser;
import mywebserver.web.http.RequestMethod;

import java.util.regex.Pattern;

public class MethodParser implements Parser<RequestMethod> {

    private static final Parser<CharSequence> verbExtractor = new SimpleRegexParser(Pattern.compile("\\s?(?<verb>(GET)|(get)|(PUT)|(put)|(POST)|(post)|(PATCH)|(patch)|(DELETE)|(delete))\\s?"), "verb") {};

    @Override
    public ParseResult<RequestMethod> parse(CharSequence input) {
        ParseResult<CharSequence> result = verbExtractor.parse(input);
        if(result.success()){
            RequestMethod reqm = RequestMethod.fromString(result.getMatched().toString());
            return new ParseResult<>(reqm, result.getRemaining(), true);
        } else {
            return new ParseResult<>(null, input, false);
        }
    }
}
