package br.com.moip.metrics.domain.model;

/**
 * Created by adercio on 27/04/17.
 */
public class Webhook implements Comparable<Webhook>{

    private int httpStatus;
    private Long count = 1L;

    public Webhook(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Long getCount() {
        return count;
    }

    public void addInscrementCount() {
        this.count = this.count + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Webhook webhook = (Webhook) o;

        return httpStatus == webhook.httpStatus;

    }

    @Override
    public int hashCode() {
        return httpStatus;
    }

    @Override
    public int compareTo(Webhook otherWebhook) {
        if (this.count > otherWebhook.count) {
            return -1;
        }
        if (this.count < otherWebhook.count) {
            return 1;
        }
        return 0;
    }
}
