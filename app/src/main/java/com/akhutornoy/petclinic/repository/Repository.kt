package com.akhutornoy.petclinic.repository

import android.accounts.NetworkErrorException
import com.akhutornoy.petclinic.entity.rest.HostResponse

class Repository(
    private val localhostRestService: LocalhostRestService
) {

    fun getAllHosts(): List<HostResponse> {
        val response = localhostRestService.getHosts().execute()

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw NetworkErrorException("Network Error code=${response.code()}, " +
                    "message=${response.message()}")
        }
    }

}
