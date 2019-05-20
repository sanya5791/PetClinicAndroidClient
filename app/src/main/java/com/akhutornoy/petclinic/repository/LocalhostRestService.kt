package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.entity.rest.HostResponse
import retrofit2.Call
import retrofit2.http.GET

interface LocalhostRestService {

    @GET("hosts")
    fun getHosts(): Call<List<HostResponse>>

}