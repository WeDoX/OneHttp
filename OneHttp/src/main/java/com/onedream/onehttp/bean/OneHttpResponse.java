package com.onedream.onehttp.bean;

import java.util.List;
import java.util.Map;

public class OneHttpResponse {
    final OneHttpRequest request;
    final int code;
    Map<String, List<String>> headers;
    final byte[] data;
    final String message;

    public OneHttpResponse(Builder builder) {
        this.request = builder.request;
        this.code = builder.code;
        this.headers = builder.headers;
        this.data = builder.data;
        this.message = builder.message;
    }

    public OneHttpRequest getRequest() {
        return request;
    }

    public int getCode() {
        return code;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public byte[] getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        OneHttpRequest request;
        int code;
        Map<String, List<String>> headers;
        byte[] data;
        String message;

        public Builder request(OneHttpRequest request) {
            this.request = request;
            return this;
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder headers(Map<String, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public Builder data(byte[] data) {
            this.data = data;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public OneHttpResponse build() {
            return new OneHttpResponse(this);
        }
    }
}
