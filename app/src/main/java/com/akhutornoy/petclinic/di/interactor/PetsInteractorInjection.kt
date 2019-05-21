package com.akhutornoy.petclinic.di.interactor

import com.akhutornoy.petclinic.di.repository.RepositoryInjection
import com.akhutornoy.petclinic.entity.mapper.PetModelMapper
import com.akhutornoy.petclinic.entity.mapper.PetRequestMapper
import com.akhutornoy.petclinic.interactor.PetsInteractor

object PetsInteractorInjection {

    fun provideInteractor() = PetsInteractor(
        RepositoryInjection.provideRepository(),
        PetModelMapper(),
        PetRequestMapper()
    )

}