package com.akhutornoy.petclinic.di.interactor

import com.akhutornoy.petclinic.di.repository.RepositoryInjection
import com.akhutornoy.petclinic.entity.mapper.HostModelMapper
import com.akhutornoy.petclinic.interactor.HostsInteractor

object HostsInteractorInjection {

    fun provideHostsInteractor() = HostsInteractor(
        RepositoryInjection.provideRepository(), HostModelMapper()
    )

}