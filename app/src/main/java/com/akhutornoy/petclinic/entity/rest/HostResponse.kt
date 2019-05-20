package com.akhutornoy.petclinic.entity.rest

import com.google.gson.annotations.SerializedName

data class HostResponse(

	@field:SerializedName("firstName")
	val firstName: String,

	@field:SerializedName("lastName")
	val lastName: String,

	@field:SerializedName("id")
	val id: Long
)