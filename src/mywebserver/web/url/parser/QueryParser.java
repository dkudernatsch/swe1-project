package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryParser implements Parser<Map<String, String>> {

    private static ParamParser paramParser = new ParamParser();

    @Override
    public ParseResult<Map<String, String>> parse(CharSequence input) {
        if (input.length() >= 1 && input.charAt(0) == '?') {
            input = input.subSequence(1, input.length());
            ParseResult<List<Map.Entry<String, String>>> result = paramParser.many(input);

            if (result.success()) {
                Map<String, String> params = result.getMatched()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                return new ParseResult<>(params, result.getRemaining(), true);
            }
        }
        return new ParseResult<>(null, input, false);

    }


    private static class ParamParser implements Parser<Map.Entry<String, String>> {

        private Parser<CharSequence> paramPairParser
                = new SimpleRegexParser(Pattern.compile("(?<paramPair>[a-zA-Z0-9$\\-_+!*()]+=[a-zA-Z0-9$\\-_+!*()]+)&?"), "paramPair") {
        };

        @Override
        public ParseResult<Map.Entry<String, String>> parse(CharSequence input) {
            ParseResult<CharSequence> result = paramPairParser.parse(input);
            if (result.success()) {
                String paramPair = result.getMatched().toString();
                String[] split = paramPair.split("=");
                assert split.length == 2;
                return new ParseResult<>(new AbstractMap.SimpleEntry<>(split[0], split[1]), result.getRemaining(), true);
            } else {
                return new ParseResult<>(null, input, false);
            }
        }
    }
}
