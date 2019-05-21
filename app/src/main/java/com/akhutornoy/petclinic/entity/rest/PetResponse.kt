package com.akhutornoy.petclinic.entity.rest

import com.google.gson.annotations.SerializedName

data class PetResponse(

	@field:SerializedName("id")
	val id: Long,

	@field:SerializedName("hostId")
	val hostId: Long,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("breed")
	val breed: String

)