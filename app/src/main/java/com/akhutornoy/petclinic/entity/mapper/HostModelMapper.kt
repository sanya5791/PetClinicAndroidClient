package com.akhutornoy.petclinic.entity.mapper

import com.akhutornoy.petclinic.entity.rest.HostResponse
import com.akhutornoy.petclinic.entity.ui.HostModel

class HostModelMapper {

    fun map(source: HostResponse) =
            HostModel(source.id, source.firstName, source.lastName)

}