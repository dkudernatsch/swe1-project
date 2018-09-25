package mywebserver.util.parser;

import java.util.ArrayList;
import java.util.List;

public interface Parser<T> {

    ParseResult<T> parse(CharSequence input);

    default ParseResult<List<T>> many(CharSequence input) {
        ParseResult<T> result;
        CharSequence currentInput = input;
        List<T> pathSegments = new ArrayList<>();

        do {
            result = parse(currentInput);
            if(result.success()){
                pathSegments.add(result.getMatched());
                currentInput = result.getRemaining();
            }

        } while (result.success());
        if(pathSegments.size() > 0){
            return new ParseResult<>(pathSegments, result.getRemaining(), true);
        } else {
            return new ParseResult<>(null, input, false);
        }
    }

}
