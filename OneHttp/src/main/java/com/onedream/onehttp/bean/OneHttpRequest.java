package com.onedream.onehttp.bean;


public class OneHttpRequest {
    private String url;
    private String method;
    private int connectTimeout;

    public OneHttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.connectTimeout = builder.connectTimeout;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public static class Builder {
        private String url;
        private String method;
        private int connectTimeout = 10_000;

        public Builder url(String url) {
            if (url == null) {
                throw new NullPointerException("url == null");
            }
            this.url = url;
            return this;
        }

        public Builder get() {
            this.method = "GET";
            return this;
        }

        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public OneHttpRequest build() {
            if (url == null) throw new IllegalStateException("url == null");
            if (method == null) {
                method = "GET";
            }
            return new OneHttpRequest(this);
        }
    }
}
