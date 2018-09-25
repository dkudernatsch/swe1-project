package mywebserver.web.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import BIF.SWE1.interfaces.Url;

public class UrlImpl implements Url {


	private String extension;

    public UrlImpl(String raw) throws URISyntaxException, MalformedURLException
    {
    }

	@Override
	public String getPath() {

		return null;
	}

	@Override
	public String getRawUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExtension() {
    	return null;
	}

	@Override
	public String getFragment() {
    	return null;
	}

	@Override
	public Map<String, String> getParameter() {
    	return null;
	}

	@Override
	public int getParameterCount() {
		return 0;
	}

	@Override
	public String[] getSegments() {
		// TODO Auto-generated method stub
		return null;
	}

}
