package com.akhutornoy.petclinic.entity.rest

import com.google.gson.annotations.SerializedName

data class HostResponse(

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("id")
	val id: Long
)