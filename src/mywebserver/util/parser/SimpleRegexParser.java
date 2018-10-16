package mywebserver.util.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SimpleRegexParser implements Parser<CharSequence> {

    private final Pattern p;
    private final String matchGroup;

    public SimpleRegexParser(Pattern p, String matchgroup){
        this.p = p;
        this.matchGroup = matchgroup;
    }

    @Override
    public ParseResult<CharSequence> parse(CharSequence input) {
        Matcher m = this.p.matcher(input);
        if(m.lookingAt()){
            return new ParseResult<>(m.group(matchGroup), input.subSequence(m.end(), input.length()), true);
        }else {
            return new ParseResult<>(null, input, false);
        }
    }
}
