package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class QueryParserTest {

    @Test
    public void testSimpleQueryParams(){

        String query = "username=hello_kitty&password=yoloswaggins";

        Parser<Map<String, String>> p = new QueryParser();
        ParseResult<Map<String, String>> result = p.parse(query);
        Assertions.assertTrue(result.success());
        Assertions.assertTrue(result.getMatched().keySet().contains("username"));
        Assertions.assertTrue(result.getMatched().keySet().contains("password"));
        Assertions.assertEquals("hello_kitty", result.getMatched().get("username"));
        Assertions.assertEquals("yoloswaggins", result.getMatched().get("password"));
        Assertions.assertEquals("", result.getRemaining());
    }

    @Test
    public void testSimpleQueryParamsWithFragment(){

        String query = "username=hello_kitty&password=yoloswaggins#yolo";

        Parser<Map<String, String>> p = new QueryParser();
        ParseResult<Map<String, String>> result = p.parse(query);
        Assertions.assertTrue(result.success());
        Assertions.assertTrue(result.getMatched().keySet().contains("username"));
        Assertions.assertTrue(result.getMatched().keySet().contains("password"));
        Assertions.assertEquals("hello_kitty", result.getMatched().get("username"));
        Assertions.assertEquals("yoloswaggins", result.getMatched().get("password"));
        Assertions.assertEquals("#yolo", result.getRemaining());

    }


}
