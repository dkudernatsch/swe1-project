package mywebserver.web.url.parser;

import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.web.url.URI;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TestUrl {

    @Test
    public void testUrl() {
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("/hello/world/?a=b&b=c#frgmnt");
        assertTrue(result.success());
        URI uri = result.getMatched();
        assertArrayEquals(new String[]{"hello", "world"}, uri.getSegments());
        assertEquals(new HashMap<String, String>(){{
            put("a", "b");
            put("b", "c");
        }}, uri.getParameter());
        assertEquals("frgmnt", uri.getFragment());
    }

    @Test
    public void testUrlIncompltete() {
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("hello#world");
        assertTrue(result.success());
        URI uri = result.getMatched();
        assertArrayEquals(new String[]{"hello"}, uri.getSegments());
        assertEquals(new HashMap<String, String>(), uri.getParameter());
        assertEquals("world", uri.getFragment());
    }

    @Test
    public void testsUrlOnlyPath(){
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("/test.jpeg");
        assertTrue(result.success());
        assertEquals("/test.jpeg", result.getMatched().getPath());
    }

    @Test
    public void testsRootPath(){
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("/ HTTP/1.1");
        assertTrue(result.success());
        assertEquals("/", result.getMatched().getPath());

    }

}
