package com.onedream.onehttp.listener;


import com.onedream.onehttp.OneHttpRealCall;
import com.onedream.onehttp.bean.OneHttpResponse;

public interface OneHttpCallback {
  void onFailure(OneHttpRealCall call, Exception e);
  void onResponse(OneHttpRealCall call, OneHttpResponse response);
}