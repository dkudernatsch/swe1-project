package mywebserver.web.url;

import BIF.SWE1.interfaces.Url;
import mywebserver.util.parser.ParseResult;
import mywebserver.web.url.parser.UrlParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class URI implements Url {

    private String              inner      = "";
    private String[]            segments   = new String[]{};
    private Map<String, String> parameters = new HashMap<>();
    private String              fragment   = "";


    public URI(String raw){
        if(raw != null) {
            UrlParser p = new UrlParser();
            ParseResult<URI> result = p.parse(raw);
            if (result.success()) {
                URI parsed = result.getMatched();
                this.inner = parsed.inner;
                this.segments = parsed.segments;
                this.parameters = parsed.parameters;
                this.fragment = parsed.fragment;
            } else {
                throw new RuntimeException("Unable to parse Url");
            }
        }
    }

    public URI(String inner,
               String[] segments,
               Map<String, String> parameters,
               String fragment) {
        this.inner = inner;
        this.segments = segments;
        this.parameters = parameters;
        this.fragment = fragment;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String getPath() {
        return Arrays.stream(segments).reduce("", (s1, s2) -> s1 + "/" + s2);
    }

    @Override
    public String getRawUrl() {
        return "".equals(inner) ? "/" : inner;
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
