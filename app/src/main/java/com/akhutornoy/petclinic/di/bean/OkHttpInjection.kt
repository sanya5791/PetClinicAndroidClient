package com.akhutornoy.petclinic.di.bean

import com.akhutornoy.petclinic.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

private const val HEADER_CONTENT_TYPE = "Content-Type"
private const val HEADER_CONTENT_TYPE_VALUE = "application/json"

object OkHttpInjection {

    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(ContentTypeHeader())
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(logging)
        }

        return builder.build()
    }

    private class ContentTypeHeader : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .addHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
                .build()
            return chain.proceed(request)
        }
    }

}