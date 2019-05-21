package com.akhutornoy.petclinic.repository

import com.akhutornoy.petclinic.entity.rest.HostRequest
import com.akhutornoy.petclinic.entity.rest.HostResponse
import com.akhutornoy.petclinic.entity.rest.PetRequest
import com.akhutornoy.petclinic.entity.rest.PetResponse
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

    fun deleteHost(hostId: Long) {
        val response = localhostRestService.deleteHost(hostId).execute()

        if (response.isSuccessful) {
            return
        } else {
            throw LocalhostException(response)
        }
    }

    fun getAllPets(hostId: Long): List<PetResponse> {
        val response = localhostRestService.getPets(hostId)
            .execute()

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            throw LocalhostException(response)
        }
    }

    fun addPet(petRequest: PetRequest) {
        val response = localhostRestService.addPet(petRequest)
            .execute()

        if (response.isSuccessful) {
            return
        } else {
            throw LocalhostException(response)
        }
    }

    fun deletePet(petId: Long) {
        val response = localhostRestService.deletePet(petId).execute()

        if (response.isSuccessful) {
            return
        } else {
            throw LocalhostException(response)
        }
    }

}
