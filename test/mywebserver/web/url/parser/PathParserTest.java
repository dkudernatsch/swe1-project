package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class PathParserTest {

    @Test
    public void testSimplePathParse(){

        String path = "/hello/world";

        Parser<List<CharSequence>> p = new PathParser();
        ParseResult<List<CharSequence>> result = p.parse(path);

        assertTrue(result.success());
        assertEquals(Arrays.asList("hello", "world"), result.getMatched());
        assertEquals("", result.getRemaining());
    }

    @Test
    public void testTrailingSlash(){

        String path = "/hello/world/";

        Parser<List<CharSequence>> p = new PathParser();
        ParseResult<List<CharSequence>> result = p.parse(path);

        assertTrue(result.success());
        assertEquals(Arrays.asList("hello", "world"), result.getMatched());
        assertEquals("", result.getRemaining());
    }

    @Test
    public void testStopsAtParamList(){

        String path = "/hello/world?a=b&b=c#yolo";

        Parser<List<CharSequence>> p = new PathParser();
        ParseResult<List<CharSequence>> result = p.parse(path);

        assertTrue(result.success());
        assertEquals(Arrays.asList("hello", "world"), result.getMatched());
        assertEquals("?a=b&b=c#yolo", result.getRemaining());
    }

    @Test
    public void testStopsAtParamListWithTrailingSlash(){

        String path = "/hello/world/?a=b&b=c#yolo";

        Parser<List<CharSequence>> p = new PathParser();
        ParseResult<List<CharSequence>> result = p.parse(path);

        assertTrue(result.success());
        assertEquals(Arrays.asList("hello", "world"), result.getMatched());
        assertEquals("?a=b&b=c#yolo", result.getRemaining());
    }

}
