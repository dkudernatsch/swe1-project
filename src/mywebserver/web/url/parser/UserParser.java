package mywebserver.web.url;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;

import java.util.regex.Pattern;

public class UserParser extends SimpleRegexParser {

    public UserParser(){
        super(Pattern.compile("(?<username>[a-zA-Z0-9_!$%&()=])@"), "username");
    }

}
