package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;

import java.util.regex.Pattern;

public class PortParser implements Parser<Integer> {

    @Override
    public ParseResult<Integer> parse(CharSequence input) {
        if(input.length() > 0){
            //has to begin with colon
            if(input.charAt(0) == ':'){
                int i = 0;
                while(i+1 < input.length()
                        && input.charAt(1+i) >= '0'
                        && input.charAt(1+i) <= '9'){
                    i++;
                }
                if(i > 0){
                    try {
                        int port = Integer.parseInt(input.subSequence(1, 1+i).toString());
                        if(port >= 0 && port < 2 << 16) {
                            return new ParseResult<>(port, input.subSequence(1 + i, input.length()), true);
                        }
                    }catch (NumberFormatException e) {
                        throw new IllegalStateException("Checked String should contain a parsable number but number was not parseable");
                    }
                }
            }
        }
        return new ParseResult<>(null, input, false);
    }
}
