package mywebserver.web.url.parser;

import java.util.regex.Pattern;

public class UserParser extends SimpleRegexParser {

    public UserParser(){
        super(Pattern.compile("(?<username>[a-zA-Z0-9_!$%&()=]+)@"), "username");
    }

}
