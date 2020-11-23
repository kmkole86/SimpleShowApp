package com.example.simpleshow.framework.datasource.network.retrofit.interceptors

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private val apiKey: String by lazy { apiKey() }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()


        request = if (request.header("No-apiKey") == null) {
            var urlWithApiKey: HttpUrl =
                request.url.newBuilder().addQueryParameter("appid", apiKey).build()
            request.newBuilder().url(urlWithApiKey).build()
        } else {
            request.newBuilder().removeHeader("No-apiKey").build()
        }

        return chain.proceed(request)
    }

    private external fun apiKey(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}