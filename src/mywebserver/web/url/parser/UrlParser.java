package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.util.parser.Tuple;
import mywebserver.web.url.URI;

import java.util.*;
import java.util.stream.Collectors;

import static mywebserver.util.parser.Parser.chain;
import static mywebserver.util.parser.Parser.opt;

public class UrlParser implements Parser<URI> {

    private static final Integer DEFAULT_PORT = 80;

    private static final Parser<Tuple.Tuple3<
                                    List<CharSequence>,
                                    Optional<Map<String, String>>,
                                    Optional<CharSequence>>>
            parser =
                chain(
                        new PathParser(),
                        opt(new QueryParser()),
                        opt(new FragmentParser())
                );

    @Override
    public ParseResult<URI> parse(CharSequence input) {
        String parts[] = input.toString().trim().split(" ");
        String raw = input.toString();
        if(parts.length > 1){
            raw = parts[0];
        }
        final String rawurl = raw;
        return ParseResult.map(parser.parse(input), (t) ->
                 new URI(
                        rawurl,
                        t.first.stream().map(Object::toString).collect(Collectors.toList()).toArray(new String[0]),
                        t.second.orElse(new HashMap<>()),
                        t.third.orElse("").toString()
                ));
    }

}
