package mywebserver.web.http;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Url;

import java.io.InputStream;
import java.util.Map;

public class InvalidRequest implements Request {
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getMethod() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public Url getUrl() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public Map<String, String> getHeaders() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public int getHeaderCount() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public String getUserAgent() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public int getContentLength() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public String getContentType() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public InputStream getContentStream() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public String getContentString() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }

    @Override
    public byte[] getContentBytes() {
        throw new UnsupportedOperationException("This is an invalid http request");
    }
}
