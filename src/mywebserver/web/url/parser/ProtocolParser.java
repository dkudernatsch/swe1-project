package mywebserver.web.url.parser;

import java.util.regex.Pattern;

public class ProtocolParser extends SimpleRegexParser {

    public ProtocolParser(){
        super(Pattern.compile("(?<protocol>[a-zA-Z]+)://"), "protocol");
    }

}
