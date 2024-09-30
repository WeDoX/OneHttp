package com.onedream.onehttp.bean;


import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OneHttpRequest {
    private Builder builder;
    private String url;
    private String method;
    private int connectTimeout;
    private OneHttpHeaders headers;


    public OneHttpRequest(Builder builder) {
        this.builder = builder;
        this.url = builder.url;
        this.method = builder.method;
        this.connectTimeout = builder.connectTimeout;
        this.headers = builder.headers.build();
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

    public OneHttpHeaders headers() {
        return headers;
    }

    public @Nullable String header(String name) {
        return headers.get(name);
    }

    public List<String> headers(String name) {
        return headers.values(name);
    }

    public Builder newBuilder() {
        return builder;
    }

    public static class Builder {
        private String url;
        private String method;
        private int connectTimeout = 10_000;
        private OneHttpHeaders.Builder headers;

        public Builder() {
            this.headers = new OneHttpHeaders.Builder();
        }

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

        /**
         * Sets the header named {@code name} to {@code value}. If this request already has any headers
         * with that name, they are all replaced.
         */
        public Builder header(String name, String value) {
            headers.set(name, value);
            return this;
        }

        /**
         * Adds a header with {@code name} and {@code value}. Prefer this method for multiply-valued
         * headers like "Cookie".
         *
         * <p>Note that for some headers including {@code Content-Length} and {@code Content-Encoding},
         * OkHttp may replace {@code value} with a header derived from the request body.
         */
        public Builder addHeader(String name, String value) {
            headers.add(name, value);
            return this;
        }

        /**
         * Removes all headers named {@code name} on this builder.
         */
        public Builder removeHeader(String name) {
            headers.removeAll(name);
            return this;
        }

        /**
         * Removes all headers on this builder and adds {@code headers}.
         */
        public Builder headers(OneHttpHeaders headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public OneHttpRequest build() {
            if (url == null) throw new IllegalStateException("url == null");
            if (method == null) {
                method = "GET";
            }
            if (headers == null) {
                headers = new OneHttpHeaders.Builder();
            }
            return new OneHttpRequest(this);
        }
    }
}
