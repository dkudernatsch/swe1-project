package uebungen;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB2;
import BIF.SWE1.interfaces.Url;
import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.web.http.InvalidRequest;
import mywebserver.web.http.parser.RequestParser;
import mywebserver.web.url.URI;

public class UEB2Impl implements UEB2 {

	@Override
	public void helloWorld() {

	}

	@Override
	public Url getUrl(String s) {
		return new URI(s);
	}

	@Override
	public Request getRequest(InputStream inputStream) {
        try {
            LineNumberReader r = new LineNumberReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            do {
                line = r.readLine();

                sb.append(line);
                sb.append('\n');

            } while (!"".equals(line));
            Parser<mywebserver.web.http.Request> p = new RequestParser();
            ParseResult<mywebserver.web.http.Request> result = p.parse(sb);

            if(result.success()){
                return result.getMatched();
            }else{
                return new InvalidRequest();
            }

        }catch (Exception e){
            return null;
        }
	}

	@Override
	public Response getResponse() {
		return null;
	}
}
