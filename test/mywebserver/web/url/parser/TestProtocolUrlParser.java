package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import org.junit.Test;

import static junit.framework.TestCase.*;


public class TestProtocolUrlParser {

    @Test
    public void testSimpleHostParse() {
        ProtocolParser p = new ProtocolParser();
        ParseResult<CharSequence> result = p.parse("https://www.hello-world.at");
        assertTrue(result.success());
        assertEquals("https", result.getMatched());
        assertEquals("www.hello-world.at", result.getRemaining());
    }

}
