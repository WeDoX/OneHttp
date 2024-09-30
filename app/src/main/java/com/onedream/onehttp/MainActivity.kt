package com.onedream.onehttp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.onedream.onehttp.bean.OneHttpRequest
import com.onedream.onehttp.bean.OneHttpResponse
import com.onedream.onehttp.interceptor.AppHeaderInterceptor
import com.onedream.onehttp.interceptor.LogInterceptor
import com.onedream.onehttp.listener.OneHttpCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        findViewById<TextView>(R.id.tv_start_request).setOnClickListener {
            requestNetwork()
        }
    }

    private fun requestNetwork() {
        //
        val request = OneHttpRequest.Builder()
            .url("http://120.78.120.117/other/github_demo_api/update_version.php")
//            .header(AppTokenService.getTokenHeaderName(), AppTokenService.getTokenHeaderValue())//添加头部，也可以使用拦截器AppHeaderInterceptor的方式
            .get()
            .build()
        //
        val httpClient = OneHttpClient.Builder()
            .addInterceptor(AppHeaderInterceptor())//添加头部拦截器，携带token
            .addInterceptor(LogInterceptor())//添加打印日志拦截器
            .build()
        //
        httpClient.newCall(request).enqueue(object :
            OneHttpCallback {
            override fun onFailure(call: OneHttpRealCall, e: Exception) {
                Log.e("ATU", "网络请求失败$e")
            }

            override fun onResponse(call: OneHttpRealCall, response: OneHttpResponse) {
                Log.e("ATU", "网络请求成功${response.code}")
            }
        })
    }
}