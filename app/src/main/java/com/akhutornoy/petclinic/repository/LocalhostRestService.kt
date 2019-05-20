package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.entity.rest.HostRequest
import com.akhutornoy.petclinic.entity.rest.HostResponse
import retrofit2.Call
import retrofit2.http.*

interface LocalhostRestService {

    @GET("hosts")
    fun getHosts(): Call<List<HostResponse>>

    @POST("addhost")
    fun addHost(@Body host: HostRequest): Call<HostResponse>

}