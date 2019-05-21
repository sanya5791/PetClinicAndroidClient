package com.akhutornoy.petclinic.entity.mapper

import com.akhutornoy.petclinic.entity.rest.PetRequest

class PetRequestMapper {

    fun map(name: String, breed: String, hostId: Long) = PetRequest(
        hostId, name, breed
    )

}