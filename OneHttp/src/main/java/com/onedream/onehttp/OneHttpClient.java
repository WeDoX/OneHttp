package com.onedream.onehttp;


import com.onedream.onehttp.Interceptor.OneHttpInterceptor;
import com.onedream.onehttp.bean.OneHttpRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OneHttpClient {
    private List<OneHttpInterceptor> interceptors = new ArrayList<>();

    private OneHttpClient(Builder builder) {
        this.interceptors = Collections.unmodifiableList(new ArrayList<>(builder.interceptors));
    }

    public List<OneHttpInterceptor> getInterceptors() {
        return interceptors;
    }

    public static final class Builder {
        final List<OneHttpInterceptor> interceptors = new ArrayList<>();


        public List<OneHttpInterceptor> interceptors() {
            return interceptors;
        }

        public Builder addInterceptor(OneHttpInterceptor interceptor) {
            if (interceptor == null) throw new IllegalArgumentException("interceptor == null");
            interceptors.add(interceptor);
            return this;
        }
        public OneHttpClient build() {
            return new OneHttpClient(this);
        }
    }

    public OneHttpRealCall newCall(OneHttpRequest request) {
        return OneHttpRealCall.newRealCall(this, request);
    }
}
