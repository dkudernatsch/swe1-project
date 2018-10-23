package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.util.parser.SimpleRegexParser;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PathParser implements Parser<List<CharSequence>> {

    private Parser<CharSequence> pathSegmentParser
            = new SimpleRegexParser(Pattern.compile("/(?<pathSegment>[a-zA-Z0-9$\\-_.+!*'()]*)"), "pathSegment"){};

    @Override
    public ParseResult<List<CharSequence>> parse(CharSequence input) {
       ParseResult<List<CharSequence>> paths = pathSegmentParser.many(input);
       if(paths.success()){
           if(paths.getRemaining().length() >= 1 && paths.getRemaining().charAt(0) == '/'){
               if(paths.getRemaining().length() == 1){
                   return new ParseResult<>(paths.getMatched(), "", true);
               }else {
                   return new ParseResult<>(paths.getMatched(), paths.getRemaining().subSequence(1, paths.getRemaining().length()), true);
               }
           }
       }
       return paths;
    }

}
