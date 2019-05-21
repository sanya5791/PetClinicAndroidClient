package com.akhutornoy.petclinic.entity.rest

import com.google.gson.annotations.SerializedName

data class PetRequest(

    @field:SerializedName("host_id")
    val hostId: Long,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("breed")
    val breed: String

)