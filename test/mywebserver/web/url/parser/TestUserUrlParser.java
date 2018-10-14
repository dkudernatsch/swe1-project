package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class TestUserUrlParser {

    @Test
    public void testSimpleUserUrl(){

        String url = "user@www.hello-world.com";

        UserParser p = new UserParser();
        ParseResult<CharSequence> result = p.parse(url);

        assertTrue(result.success());
        assertEquals("user", result.getMatched());
        assertEquals("www.hello-world.com", result.getRemaining());
    }

}
