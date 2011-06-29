package br.com.dlcoding.http.json;

/**
 * TODO (davidlins - 23/03/2011) Descrever o tipo httpServiceException.
 *
 * @author davidlins
 * @since 23/03/2011
 */
public class httpServiceException extends Exception {

    /**
     * TODO (davidlins - 23/03/2011) Descrever o campo serialVersionUID, ou remover esse comentário.
     */
    private static final long serialVersionUID = 7481183328736884611L;
    
    private String statusLine = null;
    
    private String httpErroMessage = null;

    /**
     * TODO (davidlins - 23/03/2011) Descrever o construtor httpServiceException.
     *
     */
    public httpServiceException() {
    }

    /**
     * TODO (davidlins - 23/03/2011) Descrever o construtor httpServiceException.
     *
     * @param message
     */
    public httpServiceException(String message) {
        super(message);
    }

    /**
     * TODO (davidlins - 23/03/2011) Descrever o construtor httpServiceException.
     *
     * @param cause
     */
    public httpServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * TODO (davidlins - 23/03/2011) Descrever o construtor httpServiceException.
     *
     * @param message
     * @param cause
     */
    public httpServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Retorna o atributo httpErroMessage.
     *
     * @return httpErroMessage.
     */
    public String getHttpErroMessage() {
        return httpErroMessage;
    }

    /**
     * Define o atributo httpErroMessage.
     *
     * @param httpErroMessage Novo valor para httpErroMessage.
     */
    public void setHttpErroMessage(String httpErroMessage) {
        this.httpErroMessage = httpErroMessage;
    }

    /**
     * Retorna o atributo statusLine.
     *
     * @return statusLine.
     */
    public String getStatusLine() {
        return statusLine;
    }

    /**
     * Define o atributo statusLine.
     *
     * @param statusLine Novo valor para statusLine.
     */
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }


}
