package mywebserver.web.http;

import java.util.Arrays;
import java.util.Comparator;

public enum HttpStatusCode {

    OK_200(200, "OK"),
    CREATED_201(201, "CREATED"),
    ACCEPTED_202(202, "ACCEPTED"),
    NO_CONTENT_204(204, "NO CONTENT"),

    MOVED_PERMANENTLY_301(301, "MOVED PERMANENTLY"),
    FOUND_302(302, "FOUND"),
    TEMP_REDIRECT_307(307,  "TEMPORARY REDIRECT"),

    BAD_REQUEST_400(400, "BAD REQUEST"),
    UNAUTHORIZED_401(401, "UNAUTHORIZED"),
    FORBIDDEN_403(403, "FORBIDDEN"),
    NOT_FOUND_404(404, "NOT FOUND"),
    METHOD_NOT_ALLOWED_405(405, "METHOD NOT ALLOWED"),

    SERVER_ERROR_500(500, "INTERNAL SERVER ERROR");

    HttpStatusCode(int code, String text){
        this.code = code;
        this.text = text;
    }

    private int code;
    private String text;

    public int getCode(){
        return code;
    }

    @Override
    public String toString() {
        return this.code + " " + text;
    }

    public static HttpStatusCode fromCode(int code) {
        return Arrays.stream(HttpStatusCode.values())
                .filter(status -> status.code == code)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
