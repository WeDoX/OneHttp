package com.onedream.onehttp.Interceptor;


import com.onedream.onehttp.bean.OneHttpRequest;
import com.onedream.onehttp.bean.OneHttpResponse;

import java.util.List;

public class OneHttpRealInterceptorChain implements OneHttpInterceptor.Chain {

    private final List<OneHttpInterceptor> interceptors;
    private final int index;
    private final OneHttpRequest request;

    public OneHttpRealInterceptorChain(List<OneHttpInterceptor> interceptors, int index, OneHttpRequest request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public OneHttpRequest request() {
        return request;
    }

    @Override
    public OneHttpResponse proceed(OneHttpRequest request) throws Exception{
        if (index >= interceptors.size()) {
            throw new AssertionError();
        }
        // Call the next interceptor in the chain.
        OneHttpRealInterceptorChain next = new OneHttpRealInterceptorChain(interceptors, index + 1, request);
        OneHttpInterceptor interceptor = interceptors.get(index);
        OneHttpResponse response = interceptor.intercept(next);
        return response;
    }
}
