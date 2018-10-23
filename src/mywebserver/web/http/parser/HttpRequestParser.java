package mywebserver.web.http.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.web.http.Request;
import mywebserver.web.url.parser.UrlParser;

import static mywebserver.util.parser.Parser.*;

public class HttpRequestParser implements Parser<Request> {

    private static final Parser<Request> parser =
            chain(
                    new MethodParser(),
                    new UrlParser(),
                    new HttpVersionParser(),
                    new HeaderParser())
            .map((tup) -> new Request(tup.first, tup.second, tup.third, tup.fourth));

    @Override
    public ParseResult<Request> parse(CharSequence input) {
        return parser.parse(input);
    }
}
