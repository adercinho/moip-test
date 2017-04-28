package br.com.moip.metrics.domain.model;

/**
 * Created by adercio on 27/04/17.
 */
public class Log {

    private String requestTo;
    private int response_status;


    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public int getResponse_status() {
        return response_status;
    }

    public void setResponse_status(int response_status) {
        this.response_status = response_status;
    }
}
