package com.onedream.onehttp.data_repository

/**
 *@author chenguijian
 *@since 2024/9/30
 */
object AppTokenRepository {
    fun getTokenHeaderName():String{
        return "JWTAUTHORIZATION"
    }

    fun getTokenHeaderValue():String{
        return "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwidW5pb25fZXJwX2lkIjoxLCJleHAiOjE3Mjc2Nzk1ODMsImFsZyI6IlJTMjU2IiwiaXNzIjoic3Rhcm1lcngiLCJpYXQiOjE3Mjc1MDY3ODMsInVuaW9uX2lkIjoyNzIxfQ.eHTy0uZSN3ZvNrjsixUsIC_-Ndjz7vHkWClUQ24GPdsrZiSl52gesCWAeY5vNwsGweYG-RXexkteJCC2RLAbdaCqv2TT7VgZLMUikPxETgQhYP1RFm2Y8zdEiZKetDtOTJ2RTJslTY-FHz36Zrdb_QOw1brauloLyUuXRHEGZaDk8F6bp_Li6038aa_70fU_2Kg06-2nuT_xTGriV5xahXwNuVluGT0lAcOPC2AbIIqlQ8xY4eynNY08EZ_15GpoAM_3QZ1V-pb-2v1dxoCp_hQEdeBMsQU6fjuq4sOysvWp8xbrebbl6Z6rkweAcpYosdLymvqCnrzFXOG_OeDyTA"
    }
}