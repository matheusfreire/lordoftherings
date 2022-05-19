package com.msf.lordoftherings.network

import com.msf.lordoftherings.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request =
            request.newBuilder().header("Authorization", "Bearer ${BuildConfig.API_KEY}").build()

        return chain.proceed(request)
    }
}