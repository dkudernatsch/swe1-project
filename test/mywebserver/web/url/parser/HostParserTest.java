package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HostParserTest {

    @Test
    public void testSimpleHostParser(){

        String url = "www.hello-world.com:3200/hello/world";

        Parser<CharSequence> p = new HostParser();
        ParseResult<CharSequence> result = p.parse(url);

        assertTrue(result.success());
        assertEquals("www.hello-world.com", result.getMatched());
        assertEquals(":3200/hello/world", result.getRemaining());
    }

}
