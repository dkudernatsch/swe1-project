package mywebserver.web.url.parser;

import mywebserver.util.parser.SimpleRegexParser;

import java.util.regex.Pattern;

public class FragmentParser extends SimpleRegexParser {
    public FragmentParser() {
        super(Pattern.compile("#(?<fragment>[a-zA-Z0-9$\\-_+!*()]+)"), "fragment");
    }
}
