package br.com.moip.metrics.domain.model;

/**
 * Created by adercio on 27/04/17.
 */
public class Log {

    private String requestTo;
    private int responseStatus;


    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
}
