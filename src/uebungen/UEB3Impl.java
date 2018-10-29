package uebungen;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import BIF.SWE1.interfaces.Plugin;
import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB3;
import mywebserver.plugins.TestPlugin;
import mywebserver.util.parser.ParseResult;
import mywebserver.util.parser.Parser;
import mywebserver.web.http.HttpResponse;
import mywebserver.web.http.InvalidRequest;
import mywebserver.web.http.parser.RequestParser;

public class UEB3Impl implements UEB3 {

	@Override
	public void helloWorld() {

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
		return new HttpResponse();
	}

	@Override
	public Plugin getTestPlugin() {
		return new TestPlugin();
	}
}
