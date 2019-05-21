package com.akhutornoy.petclinic.entity.mapper

import com.akhutornoy.petclinic.entity.rest.PetResponse
import com.akhutornoy.petclinic.entity.ui.PetModel

class PetModelMapper {

    fun map(source: PetResponse) = PetModel(
        source.id,
        source.name,
        source.breed
    )

}