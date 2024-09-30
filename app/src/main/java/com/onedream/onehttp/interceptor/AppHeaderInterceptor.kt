package com.onedream.onehttp.interceptor

import com.onedream.onehttp.Interceptor.OneHttpInterceptor
import com.onedream.onehttp.data_repository.AppTokenRepository
import com.onedream.onehttp.bean.OneHttpRequest
import com.onedream.onehttp.bean.OneHttpResponse

/**
 *@author chenguijian
 *@since 2024/9/30
 */
class AppHeaderInterceptor : OneHttpInterceptor {

    @Throws(Exception::class)
    override fun intercept(chain: OneHttpInterceptor.Chain): OneHttpResponse {
        val builder: OneHttpRequest.Builder = chain.request().newBuilder()
        //头部携带token
        builder.addHeader(AppTokenRepository.getTokenHeaderName(), AppTokenRepository.getTokenHeaderValue())
        //请求信息
        return chain.proceed(builder.build())
    }
}