package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PathParser implements Parser<List<CharSequence>> {

    private Parser<CharSequence> pathSegmentParser
            = new SimpleRegexParser(Pattern.compile("/?(?<pathSegment>[a-zA-Z0-9$\\-_.+!*'()]+)/?"), "pathSegment"){};

    @Override
    public ParseResult<List<CharSequence>> parse(CharSequence input) {
        return pathSegmentParser.many(input);
    }

}
