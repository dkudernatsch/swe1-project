package uebungen;

import BIF.SWE1.interfaces.UEB1;
import BIF.SWE1.interfaces.Url;
import mywebserver.web.url.URI;

public class UEB1Impl implements UEB1 {

	@Override
	public Url getUrl(String path) {
		return new URI(path);
	}

	@Override
	public void helloWorld() {
	}
}
