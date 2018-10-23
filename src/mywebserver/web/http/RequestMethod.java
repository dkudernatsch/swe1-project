package mywebserver.web.http;

public enum RequestMethod {
    GET, HEAD, PUT, POST, PATCH, DELETE;

    public static RequestMethod fromString(String from) {
        switch (from) {
            case "GET": return GET;
            case "get": return GET;
            
            case "PUT": return PUT;
            case "put": return PUT;

            case "HEAD": return HEAD;
            case "head": return HEAD;

            case "POST": return POST;
            case "post": return POST;

            case "PATCH": return PATCH;
            case "patch": return PATCH;

            case "DELETE": return DELETE;
            case "delete": return DELETE;
        }
        return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case GET: return "GET";
            case PUT: return "PUT";
            case HEAD: return "HEAD";
            case POST: return "POST";
            case PATCH: return "PATCH";
            case DELETE: return "DELETE";
        }
        assert false;
        return "";
    }
}
