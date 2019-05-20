package com.akhutornoy.petclinic.entity.mapper

import com.akhutornoy.petclinic.entity.rest.HostRequest

class HostRequestMapper {

    fun map(firstName: String, lastName: String) = HostRequest(firstName, lastName)

}