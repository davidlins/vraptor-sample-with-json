package br.com.dlcoding.http.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * Simple class for HttpServiceInvoke using Json.
 * 
 * @author David Lins
 * @since 11/03/2011
 */
public class HttpServiceInvoke {

	private HttpServiceConnection httpConnection;
	private Charset charset = Charset.defaultCharset();

	public HttpServiceInvoke() {
		this(new DefaultHttpServiceConnection());
	}

	public HttpServiceInvoke(HttpServiceConnection httpServiceConnection) {
		this(httpServiceConnection, null);

	}

	public HttpServiceInvoke(HttpServiceConnection httpServiceConnection,
			Charset charset) {
		super();
		this.httpConnection = httpServiceConnection;
	}

	@SuppressWarnings("unchecked")
	public <O extends Object> O invoke(String uri, Class<?>... responseClass)
			throws httpServiceException {
		return (O) invoke(uri, null, responseClass);
	}

	@SuppressWarnings("unchecked")
	public <O extends Object> O invoke(String uri, Object request,
			Class<?>... responseClass) throws httpServiceException {

		InputStream in = null;
		Object object = null;

		if (request != null) {
			String json = marshall(request);
			in = this.httpConnection.post(uri, json.getBytes(this.charset));
		} else {
			in = this.httpConnection.get(uri);
		}

		if (in != null)
			object = unmarshall(in, responseClass);

		return (O) object;

	}

	public String marshall(Object request) {

		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.processAnnotations(request.getClass());

		return xstream.toXML(request);

	}

	public Object unmarshall(InputStream in, Class<?>... responseClass) {

		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.processAnnotations(responseClass);

		return xstream.fromXML(new InputStreamReader(in, this.charset));

	}

}
