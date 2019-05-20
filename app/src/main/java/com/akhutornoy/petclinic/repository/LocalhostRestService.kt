package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.entity.rest.HostRequest
import com.akhutornoy.petclinic.entity.rest.HostResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LocalhostRestService {

    @GET("hosts")
    fun getHosts(): Call<List<HostResponse>>

    @POST("addhost")
    fun addHost(@Body host: HostRequest): Call<HostResponse>

    @GET("deletehost")
    fun deleteHost(@Query("host_id") hostId: Long): Call<ResponseBody>

}