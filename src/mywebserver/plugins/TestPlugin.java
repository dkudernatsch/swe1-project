package mywebserver.plugins;


import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import mywebserver.web.http.HttpResponse;

public class TestPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        return 1;
    }

    @Override
    public Response handle(Request req) {
        if(req.isValid()){
            return new HttpResponse()
                    .withContent("Hello World")
                    .withStatus(200);
        } else {
            return new HttpResponse()
                    .withStatus(400);
        }
    }
}
