# OneHttp

~~~~~~~~
 private fun requestNetwork(){
        //
        val request = OneHttpRequest.Builder()
            .url("http://120.78.120.117/other/github_demo_api/update_version.php")
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
~~~~~~~~
