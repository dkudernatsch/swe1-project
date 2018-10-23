package mywebserver.web.http;

import BIF.SWE1.interfaces.Url;

import java.io.InputStream;
import java.util.Map;

public class Request implements BIF.SWE1.interfaces.Request {


    private final Url url;
    private final RequestMethod method;
    private final HttpVersion version;
    private final Map<String, String> headers;

    public Request(RequestMethod method, Url url, HttpVersion version, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.version = version;
        this.headers = headers;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getMethod() {
        return method.toString();
    }

    @Override
    public Url getUrl() {
        return url;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public int getHeaderCount() {
        return headers.size();
    }

    @Override
    public String getUserAgent() {
        return headers.get("Useragent");
    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public InputStream getContentStream() {
        return null;
    }

    @Override
    public String getContentString() {
        return null;
    }

    @Override
    public byte[] getContentBytes() {
        return new byte[0];
    }
}
