package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PortParserTest {

    @Test
    public void testSimplePortParser(){
        Parser<Integer> p = new PortParser();
        ParseResult<Integer> result = p.parse(":3200");
        assertTrue(result.success());
        assertEquals(new Integer(3200), result.getMatched());
        assertEquals("", result.getRemaining());
    }

    @Test
    public void testPortToLarge(){
        Parser<Integer> p = new PortParser();
        ParseResult<Integer> result = p.parse(":333200/blah");
        assertFalse(result.success());
        assertNull(result.getMatched());
        assertEquals(":333200/blah", result.getRemaining());
    }

}
