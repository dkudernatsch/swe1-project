package mywebserver.web.http.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.util.parser.SimpleRegexParser;
import mywebserver.web.http.HttpVersion;

import java.util.regex.Pattern;

public class HttpVersionParser implements Parser<HttpVersion> {

    private static final Parser<CharSequence> httpVersionExtractor = new SimpleRegexParser(
            Pattern.compile("HTTP/(?<version>[^\\s]+)"),
            "version"
    ) {};

    @Override
    public ParseResult<HttpVersion> parse(CharSequence input) {
        ParseResult<CharSequence> result = httpVersionExtractor.parse(input);
        if(result.success()){
            switch (result.getMatched().toString()) {
                case "1": return new ParseResult<>(HttpVersion.V1, result.getRemaining(), true);
                case "1.1": return new ParseResult<>(HttpVersion.V1_1, result.getRemaining(), true);
                case "2": return new ParseResult<>(HttpVersion.V2, result.getRemaining(), true);
            }
        }
        return new ParseResult<>(null, input, false);
    }
}
