package com.onedream.onehttp

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
        //
        Log.e("ATU","http请求url:${oneNetworkResponse.request.url}")
        Log.e("ATU","http状态码:${oneNetworkResponse.code}")
        if(null != oneNetworkResponse.headers){
            for(value in oneNetworkResponse.headers.keys){
                Log.e("ATU","http头部:$value《=====》${oneNetworkResponse.headers[value].toString()}")
            }
        }
        Log.e("ATU","http状态消息:${oneNetworkResponse.message}")
        if(null == oneNetworkResponse.data){
            Log.e("ATU","http返回内容:为空")
        }else {
            printLogResponseBody(String(oneNetworkResponse.data, Charsets.UTF_8))
        }
        return oneNetworkResponse
    }

    private fun printLogResponseBody(data : String){
        if(LINE_SEPARATOR == null){
            Log.e("ATU","http返回内容:callback===>${getJsonString(data)}")
        }else{
            Log.e("ATU","http返回内容:callback===>")
            val logArray = getJsonString(data).split(LINE_SEPARATOR)
            for(logItem in logArray){
                Log.e("ATU", "http返回内容: $logItem")
            }
        }
    }

    private val JSON_INDENT = 3
    private val LINE_SEPARATOR = System.getProperty("line.separator")

    private fun getJsonString(msg: String): String{
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