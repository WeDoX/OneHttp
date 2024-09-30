package com.onedream.onehttp.interceptor

import android.util.Log
import com.onedream.onehttp.Interceptor.OneHttpInterceptor
import com.onedream.onehttp.bean.OneHttpResponse
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class LogInterceptor : OneHttpInterceptor {

    @Throws(Exception::class)
    override fun intercept(chain: OneHttpInterceptor.Chain): OneHttpResponse {
        val request = chain.request();
        val oneNetworkResponse = chain.proceed(request)
        //打印请求
        Log.e("ATU", "--> " + request.method + ' '.toString() + request.url + ' '.toString())
        //设置头部
        val requestHeaders = request.headers()
        if (null != requestHeaders) {
            val size: Int = requestHeaders.size()
            for (index in 0 until size) {
                Log.e("ATU", "${requestHeaders.name(index)}:${requestHeaders.value(index)}")
            }
        }

        //打印返回
        Log.e(
            "ATU", "<-- "
                    + oneNetworkResponse.code
                    + ' '.toString()
                    + oneNetworkResponse.message
                    + ' '.toString()
                    + oneNetworkResponse.request.url
        )

        if (null != oneNetworkResponse.headers) {
            for (value in oneNetworkResponse.headers.keys) {
                Log.e("ATU", "$value:${oneNetworkResponse.headers[value].toString()}")
            }
        }
        //
        if (null == oneNetworkResponse.data) {
            Log.e("ATU", "callback===>null")
        } else {
            printLogResponseBody(String(oneNetworkResponse.data, Charsets.UTF_8))
        }
        Log.e("ATU", "<-- END HTTP")
        return oneNetworkResponse
    }

    private fun printLogResponseBody(data: String) {
        if (LINE_SEPARATOR == null) {
            Log.e("ATU", "callback===>${getJsonString(data)}")
        } else {
            Log.e("ATU", "callback===>")
            val logArray = getJsonString(data).split(LINE_SEPARATOR)
            for (logItem in logArray) {
                Log.e("ATU", logItem)
            }
        }
    }

    private val JSON_INDENT = 3
    private val LINE_SEPARATOR = System.getProperty("line.separator")

    private fun getJsonString(msg: String): String {
        return try {
            if (msg.startsWith("{")) {
                val jsonObject = JSONObject(msg)
                jsonObject.toString(JSON_INDENT)
            } else if (msg.startsWith("[")) {
                val jsonArray = JSONArray(msg)
                jsonArray.toString(JSON_INDENT)
            } else {
                msg
            }
        } catch (e: JSONException) {
            msg
        }
    }
}