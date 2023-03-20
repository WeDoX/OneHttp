package com.onedream.onehttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.onedream.onehttp.bean.OneHttpRequest
import com.onedream.onehttp.bean.OneHttpResponse
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

    private fun requestNetwork(){
        //
        val request = OneHttpRequest.Builder()
            .url("http://120.78.120.117/github_demo_api/update_version.php")
            .get()
            .build()
        //
        val httpClient = OneHttpClient.Builder()
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