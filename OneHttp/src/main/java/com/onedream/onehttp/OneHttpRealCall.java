package com.onedream.onehttp;


import com.onedream.onehttp.Interceptor.OneHttpBridgeInterceptor;
import com.onedream.onehttp.Interceptor.OneHttpCallServerInterceptor;
import com.onedream.onehttp.Interceptor.OneHttpInterceptor;
import com.onedream.onehttp.Interceptor.OneHttpRealInterceptorChain;
import com.onedream.onehttp.bean.OneHttpRequest;
import com.onedream.onehttp.bean.OneHttpResponse;
import com.onedream.onehttp.listener.OneHttpCallback;

import java.util.ArrayList;
import java.util.List;

public class OneHttpRealCall {
    private OneHttpClient oneNetworkHttpClient;
    private OneHttpRequest originalRequest;

    private OneHttpRealCall(OneHttpClient oneNetworkHttpClient, OneHttpRequest originalRequest) {
        this.oneNetworkHttpClient = oneNetworkHttpClient;
        this.originalRequest = originalRequest;
    }

    public static OneHttpRealCall newRealCall(OneHttpClient oneNetworkHttpClient, OneHttpRequest originalRequest) {
        OneHttpRealCall call = new OneHttpRealCall(oneNetworkHttpClient, originalRequest);
        return call;
    }

    public void enqueue(final OneHttpCallback oneNetworkCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OneHttpResponse response = getResponseWithInterceptorChain();
                    oneNetworkCallback.onResponse(OneHttpRealCall.this, response);
                } catch (Exception e) {
                    oneNetworkCallback.onFailure(OneHttpRealCall.this, e);
                }
            }
        }).start();
    }

    private OneHttpResponse getResponseWithInterceptorChain() throws Exception {
        // Build a full stack of interceptors.
        List<OneHttpInterceptor> interceptors = new ArrayList<>();
        interceptors.addAll(oneNetworkHttpClient.getInterceptors());
        interceptors.add(new OneHttpBridgeInterceptor());
        interceptors.add(new OneHttpCallServerInterceptor());
        //
        OneHttpInterceptor.Chain chain = new OneHttpRealInterceptorChain(interceptors, 0,
                originalRequest);

        return chain.proceed(originalRequest);
    }
}
