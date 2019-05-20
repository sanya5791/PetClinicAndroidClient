package com.akhutornoy.petclinic.interactor

import com.akhutornoy.petclinic.entity.mapper.HostModelMapper
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.repository.Repository

class HostsInteractor(
    private val repository: Repository,
    private val hostModelMapper: HostModelMapper
) {

    fun getAllHosts(): List<HostModel> {
        return repository
            .getAllHosts()
            .map(hostModelMapper::map)
    }

}