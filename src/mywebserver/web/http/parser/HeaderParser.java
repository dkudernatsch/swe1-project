package mywebserver.web.http.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.util.parser.SimpleRegexParser;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HeaderParser implements Parser<Map<String, String>> {

    private static final Parser<CharSequence> headerLineExtractor = new SimpleRegexParser(Pattern.compile("\\s*(?<line>[^\\s]+\\s?:.*?)\\n"), "line") {};

    private static final String headerPairSeparator = ":";

    @Override
    public ParseResult<Map<String, String>> parse(CharSequence input) {
        ParseResult<List<CharSequence>> many = headerLineExtractor.many(input);
        return ParseResult.map(many, (list) ->
                list.stream().map((str) -> {
                    String[] split = str.toString().split(headerPairSeparator);
                    assert split.length == 2;
                    return new AbstractMap.SimpleEntry<>(split[0].trim(), split[1].trim());
                }).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

}
