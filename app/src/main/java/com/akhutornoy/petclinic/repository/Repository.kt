package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.entity.rest.HostRequest
import com.akhutornoy.petclinic.entity.rest.HostResponse
import com.akhutornoy.petclinic.repository.exception.LocalhostException

class Repository(
    private val localhostRestService: LocalhostRestService
) {

    fun getAllHosts(): List<HostResponse> {
        val response = localhostRestService.getHosts()
            .execute()

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw LocalhostException(response)
        }
    }

    fun addHost(hostRequest: HostRequest) {
        val response = localhostRestService.addHost(hostRequest)
            .execute()

        if (response.isSuccessful) {
            return
        } else {
            throw LocalhostException(response)
        }
    }

}
