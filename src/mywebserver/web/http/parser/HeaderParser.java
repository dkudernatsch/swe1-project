package mywebserver.web.http.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.util.parser.SimpleRegexParser;
import mywebserver.util.parser.Tuple;

import java.util.Map;
import java.util.regex.Pattern;

public class HeaderParser implements Parser<Map<String, String>> {



    @Override
    public ParseResult<Map<String, String>> parse(CharSequence input) {
        return null;
    }

    private static final class HeaderPairParser implements Parser<Map.Entry<String, String>> {

        private static final Parser<CharSequence> headerLineExtractor = new SimpleRegexParser(Pattern.compile("\\s*(?<line>[^\\s]+\\s?:.*?)\\r\\n"), "line") {};

        @Override
        public ParseResult<Map.Entry<String, String>> parse(CharSequence input) {
            ParseResult<CharSequence> many =
        }
    }
}
