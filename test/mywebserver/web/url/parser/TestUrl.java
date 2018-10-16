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
        ParseResult<URI> result = p.parse("http://user@orf.at:8080/hello/world/?a=b&b=c#frgmnt");
        assertTrue(result.success());
        URI uri = result.getMatched();
        assertEquals("http", uri.getProtocol());
        assertEquals("user", uri.getUsername());
        assertEquals("orf.at", uri.getHost());
        assertEquals(Integer.valueOf(8080), uri.getPort());
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
        ParseResult<URI> result = p.parse("http://orf.at/hello#world");
        assertTrue(result.success());
        URI uri = result.getMatched();
        assertEquals("http", uri.getProtocol());
        assertEquals("", uri.getUsername());
        assertEquals("orf.at", uri.getHost());
        assertEquals(Integer.valueOf(80), uri.getPort());
        assertArrayEquals(new String[]{"hello"}, uri.getSegments());
        assertEquals(new HashMap<String, String>(), uri.getParameter());
        assertEquals("world", uri.getFragment());
    }

    @Test
    public void testUrlFailsOnMissingProtocol() {
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("www.orf.at/hello#world");
        assertFalse(result.success());
    }

    @Test
    public void testsUrlFailsOnMissingHost(){
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("http://hello#world");
        assertFalse(result.success());
    }

    @Test
    public void testsUrlLocalhost(){
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("http://hello#w7e 2q09q3EṔ+Þ~3WRR3ßü9 r3ẞ9+EWF WF?) EQ=)(orld");
        assertFalse(result.success());
    }

    @Test
    public void testsUrlOnlyPath(){
        Parser<URI> p = new UrlParser();
        ParseResult<URI> result = p.parse("/test.jpeg");
        assertTrue(result.success());
        assertEquals("/test.jpeg", result.getMatched().getPath());
    }


}
