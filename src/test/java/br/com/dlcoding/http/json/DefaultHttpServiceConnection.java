package br.com.dlcoding.http.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This class provider a simple Http Service Connection
 * 
 * @author David lins
 * @since 15/03/2011
 */
public class DefaultHttpServiceConnection implements HttpServiceConnection {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dlcoding.rest.client.HttpServiceConnection#post(java.lang.String,
	 * byte[])
	 */
	@Override
	public InputStream post(String uri, byte[] data)
			throws httpServiceException {

		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader("Content-Type", "application/json");

		try {
			httpPost.setEntity(new StringEntity(new String(data)));
		} catch (UnsupportedEncodingException e) {
			throw new httpServiceException("Enconding not supported!", e);
		}

		return execute(httpPost);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.dlcoding.rest.client.HttpServiceConnection#get(java.lang.String)
	 */
	@Override
	public InputStream get(String uri) throws httpServiceException {
		return execute(new HttpGet(uri));
	}

	/**
	 * 
	 * Executa um request ao servico disponiblizado.
	 * 
	 * @param httpUriRequest
	 * @return {@link InputStream}
	 */
	protected InputStream execute(HttpUriRequest httpUriRequest)
			throws httpServiceException {

		InputStream in = null;

		try {

			HttpClient httpclient = new DefaultHttpClient();
			in = handleResponse(httpclient.execute(httpUriRequest),
					httpUriRequest.getURI().toString());

		} catch (Exception e) {
			httpServiceException ex = null;

			if (e instanceof httpServiceException) {
				ex = (httpServiceException) e;
			} else {
				throw new httpServiceException("Erro ao executar o servico: "
						+ httpUriRequest.getURI(), e);
			}
			throw ex;

		}

		return in;

	}

	protected InputStream handleResponse(HttpResponse response, String uri)
			throws httpServiceException {

		InputStream in = null;

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode != 200) {
			String statusLine = response.toString();
			httpServiceException e = new httpServiceException(
					"Error on call the service \"" + uri + "\": " + statusLine);
			e.setStatusLine(statusLine);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {

				try {

					in = entity.getContent();
					byte[] b = new byte[in.available()];
					in.read(b);
					e.setHttpErroMessage(new String(b));

				} catch (Exception e1) {
					// TODO: Colocar o log4j
					e.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e1) {
							// TODO: Colocar o log4j
							e1.printStackTrace();
						}
					}
				}
			}
			throw e;
		}

		try {

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				in = entity.getContent();
			}

		} catch (Exception e) {
			httpServiceException ex = null;

			if (e instanceof httpServiceException) {
				ex = (httpServiceException) e;
			} else {
				throw new httpServiceException("Error on call the service \""+uri+"\" ",e);
			}
			throw ex;
		}

		return in;

	}

}
