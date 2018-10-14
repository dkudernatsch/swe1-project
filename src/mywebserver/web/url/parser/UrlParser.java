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

    @Override
    public ParseResult<URI> parse(CharSequence input) {

        Parser<Tuple.Tuple7<
                Optional<CharSequence>,
                Optional<CharSequence>,
                Optional<CharSequence>,
                Optional<Integer>,
                Optional<List<CharSequence>>,
                Optional<Map<String, String>>,
                Optional<CharSequence>>>
        parser =
            chain(
                opt(new ProtocolParser()),
                opt(new UserParser()),
                opt(new HostParser()),
                opt(new PortParser()),
                opt(new PathParser()),
                opt(new QueryParser()),
                opt(new FragmentParser())
            );

        return ParseResult.map(parser.parse(input), (t) ->
                new URI(
                        input.toString(),
                        t.first.orElse("").toString(),
                        t.second.orElse("").toString(),
                        t.third.orElse("").toString(),
                        t.fourth.orElse(DEFAULT_PORT),
                        t.fifth.orElse(new ArrayList<>()).stream().map(Object::toString).collect(Collectors.toList()).toArray(new String[0]),
                        t.sixth.orElse(new HashMap<>()),
                        t.seventh.orElse("").toString()
                        ));

    }

    private ParseResult<CharSequence> protcol(CharSequence input) {
        return new HostParser().parse(input);
    }
}
