package mywebserver.web.url.parser;

import mywebserver.util.parser.SimpleRegexParser;

import java.util.regex.Pattern;

public class HostParser extends SimpleRegexParser {

    public HostParser() {
        super(Pattern.compile("(?<host>(([a-zA-Z0-9$\\-_+!*()]+)(\\.[a-zA-Z0-9$\\-_+!*'(),]+)*))"), "host");
    }
}
