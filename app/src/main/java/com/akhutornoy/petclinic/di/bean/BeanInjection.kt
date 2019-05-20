package com.akhutornoy.petclinic.di.bean

import com.akhutornoy.petclinic.repository.LocalhostRestService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//this address should be changed according to wifi endpoint
private const val END_POINT = "http://192.168.100.149:8081/"
object BeanInjection {

    fun provideRestService() =
        provideRetrofit().create(LocalhostRestService::class.java)

    private fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl(END_POINT)
            .addConverterFactory(provideGsonConverterFactory())
            .client(OkHttpInjection.provideOkHttpClient())
            .build()

    private fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

}