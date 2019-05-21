package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.entity.rest.HostRequest
import com.akhutornoy.petclinic.entity.rest.HostResponse
import com.akhutornoy.petclinic.entity.rest.PetRequest
import com.akhutornoy.petclinic.entity.rest.PetResponse
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

    @GET("pets")
    fun getPets(@Query("host_id") hostId: Long): Call<List<PetResponse>>

    @POST("addpet")
    fun addPet(@Body pet: PetRequest): Call<PetResponse>

    @GET("deletepet")
    fun deletePet(@Query("pet_id") petId: Long): Call<ResponseBody>

}