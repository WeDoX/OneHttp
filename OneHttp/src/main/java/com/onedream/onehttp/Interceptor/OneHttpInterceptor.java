package com.onedream.onehttp.Interceptor;


import com.onedream.onehttp.bean.OneHttpRequest;
import com.onedream.onehttp.bean.OneHttpResponse;

public interface OneHttpInterceptor {
  OneHttpResponse intercept(Chain chain) throws Exception;

  interface Chain {
    OneHttpRequest request();

    OneHttpResponse proceed(OneHttpRequest request) throws Exception;
  }
}