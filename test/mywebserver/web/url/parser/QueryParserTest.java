package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class QueryParserTest {

    @Test
    public void testSimpleQueryParams(){

        String query = "username=hello_kitty&password=yoloswaggins";

        Parser<Map<String, String>> p = new QueryParser();
        ParseResult<Map<String, String>> result = p.parse(query);
        assertTrue(result.success());
        assertTrue(result.getMatched().keySet().contains("username"));
        assertTrue(result.getMatched().keySet().contains("password"));
        assertEquals("hello_kitty", result.getMatched().get("username"));
        assertEquals("yoloswaggins", result.getMatched().get("password"));
        assertEquals("", result.getRemaining());
    }

    @Test
    public void testSimpleQueryParamsWithFragment(){

        String query = "username=hello_kitty&password=yoloswaggins#yolo";

        Parser<Map<String, String>> p = new QueryParser();
        ParseResult<Map<String, String>> result = p.parse(query);
        assertTrue(result.success());
        assertTrue(result.getMatched().keySet().contains("username"));
        assertTrue(result.getMatched().keySet().contains("password"));
        assertEquals("hello_kitty", result.getMatched().get("username"));
        assertEquals("yoloswaggins", result.getMatched().get("password"));
        assertEquals("#yolo", result.getRemaining());

    }


}
