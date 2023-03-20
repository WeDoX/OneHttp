package com.onedream.onehttp.Interceptor;


import com.onedream.onehttp.bean.OneHttpRequest;
import com.onedream.onehttp.bean.OneHttpResponse;

public class OneHttpBridgeInterceptor implements OneHttpInterceptor {

    @Override
    public OneHttpResponse intercept(Chain chain) throws Exception {
        OneHttpRequest request = chain.request();
        OneHttpResponse response = chain.proceed(request);
        return response;

    }
}
