package mywebserver.web.http;

import BIF.SWE1.interfaces.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mywebserver.util.functional.RuntimeBiConsumer;

public class HttpResponse implements Response {

    private Map<String, String> headers = new HashMap<>();

    private String contentType;
    private int contentLength;
    private byte[] content;

    private HttpStatusCode statusCode;

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public int getStatusCode() {
        if(statusCode != null)
            return statusCode.getCode();
        else throw new RuntimeException("Status code was not set!");
    }

    @Override
    public void setStatusCode(int status) {
        this.statusCode = HttpStatusCode.fromCode(status);
    }

    public HttpResponse withStatus(int status){
        this.statusCode = HttpStatusCode.fromCode(status);
        return this;
    }

    @Override
    public String getStatus() {
        return statusCode.toString();
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public String getServerHeader() {
        return null;
    }

    @Override
    public void setServerHeader(String server) {

    }

    @Override
    public void setContent(String content) {
        this.contentLength = content.length();
        this.content = content.getBytes();
    }

    @Override
    public void setContent(byte[] content) {
        this.contentLength = content.length;
        this.content = content;
    }

    public HttpResponse withContent(String content){
        setContent(content);
        return this;
    }

    @Override
    public void setContent(InputStream stream) {

    }

    @Override
    public void send(OutputStream network) {
        try {
            network.write("HTTP 1.1 ".getBytes());
            network.write(this.statusCode.toString().getBytes());
            network.write("\n".getBytes());
            headers.forEach(
                    (RuntimeBiConsumer) (k, v) -> network.write( (k + ": " + v + "\n").getBytes() )
            );
            network.write("\n".getBytes());
            network.write(content);
        }catch (IOException e){
            throw new RuntimeException("THIS IS STUPID");
        }

    }


}
