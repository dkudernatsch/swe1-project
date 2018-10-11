package mywebserver.web.url;

import BIF.SWE1.interfaces.Url;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class URI implements Url {

    private String inner;
    private String protocol;
    private String username;
    private String host;
    private Integer port;
    private String[] segments;
    private Map<String, String> parameters;
    private String fragment;

    public URI(String inner,
               String protocol,
               String username,
               String host,
               Integer port,
               String[] segments,
               Map<String, String> parameters,
               String fragment) {
        this.inner = inner;
        this.protocol = protocol;
        this.username = username;
        this.host = host;
        this.port = port;
        this.segments = segments;
        this.parameters = parameters;
        this.fragment = fragment;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String getPath() {
        return Arrays.stream(segments).reduce((s1, s2) -> s1 + "/" + s2).orElse("");
    }

    @Override
    public String getRawUrl() {
        return inner;
    }

    @Override
    public Map<String, String> getParameter() {
        return parameters;
    }

    @Override
    public int getParameterCount() {
        return parameters.size();
    }

    @Override
    public String[] getSegments() {
        return segments;
    }

    @Override
    public String getFileName() {
        return segments.length > 0
                && segments[segments.length - 1].indexOf(".") > 0
                ? segments[segments.length - 1]
                : "";
    }

    @Override
    public String getExtension() {
        String file = getFileName();
        if("".equals(file))
            return file;
        else
            return file.substring(file.indexOf("."), file.length());
    }

    @Override
    public String getFragment() {
        return fragment;
    }
}
