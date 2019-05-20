package com.akhutornoy.petclinic.interactor

import com.akhutornoy.petclinic.entity.mapper.HostModelMapper
import com.akhutornoy.petclinic.entity.mapper.HostRequestMapper
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.repository.Repository

class HostsInteractor(
    private val repository: Repository,
    private val hostModelMapper: HostModelMapper,
    private val hostRequestMapper: HostRequestMapper
) {

    fun getAllHosts(): List<HostModel> {
        return repository
            .getAllHosts()
            .map(hostModelMapper::map)
    }

    fun addHost(firstName: String, lastName: String) {
        repository.addHost(hostRequestMapper.map(firstName, lastName))
    }

}