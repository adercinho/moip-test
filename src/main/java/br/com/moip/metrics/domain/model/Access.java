package br.com.moip.metrics.domain.model;

/**
 * Created by adercio on 27/04/17.
 */
public class Access implements Comparable<Access>{

    private String url;
    private Long count = 1L;

    public Access(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void addInscrementCount() {
        this.count = this.count + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Access access = (Access) o;

        return !(url != null ? !url.equals(access.url) : access.url != null);

    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public int compareTo(Access otherAccess) {
        if (this.count > otherAccess.getCount()) {
            return -1;
        }
        if (this.count < otherAccess.getCount()) {
            return 1;
        }
        return 0;
    }
}
