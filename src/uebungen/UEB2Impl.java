package uebungen;

import java.io.InputStream;

import BIF.SWE1.interfaces.Request;
import BIF.SWE1.interfaces.Response;
import BIF.SWE1.interfaces.UEB2;
import BIF.SWE1.interfaces.Url;
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
		return null;
	}

	@Override
	public Response getResponse() {
		return null;
	}
}
