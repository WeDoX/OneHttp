package com.onedream.onehttp.data_repository

/**
 *@author chenguijian
 *@since 2024/9/30
 */
object AppTokenRepository {
    fun getTokenHeaderName():String{
        return "Authorization"
    }

    fun getTokenHeaderValue():String{
        return "Bearer valid_token_123"
    }
}