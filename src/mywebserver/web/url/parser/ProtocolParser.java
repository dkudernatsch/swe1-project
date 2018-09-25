package mywebserver.web.url;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProtocolParser extends SimpleRegexParser {

    public ProtocolParser(){
        super(Pattern.compile("(?<protocol>[a-zA-Z]+)://"), "protocol");
    }

}
