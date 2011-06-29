package br.com.dlcoding.http.json;

import java.io.InputStream;

/**
 * TODO (davidlins - 15/03/2011) Descrever o tipo HttpServiceConnection.
 *
 * @author davidlins
 * @since 15/03/2011
 */
public interface HttpServiceConnection {
    
    public InputStream post(String uri,byte[] data) throws httpServiceException;
    
    public InputStream get(String uri) throws httpServiceException;
    
}
