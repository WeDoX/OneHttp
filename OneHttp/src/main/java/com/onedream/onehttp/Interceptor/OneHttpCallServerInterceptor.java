package com.onedream.onehttp.Interceptor;


import com.onedream.onehttp.bean.OneHttpRequest;
import com.onedream.onehttp.bean.OneHttpResponse;
import com.onedream.onehttp.core.OneHttpCore;

public class OneHttpCallServerInterceptor implements OneHttpInterceptor {

    @Override
    public OneHttpResponse intercept(Chain chain) throws Exception {
        OneHttpRequest request = chain.request();
        return OneHttpCore.request(request);
    }
}
