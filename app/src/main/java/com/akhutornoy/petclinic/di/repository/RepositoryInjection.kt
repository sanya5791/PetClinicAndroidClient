package com.akhutornoy.petclinic.di.repository

import com.akhutornoy.petclinic.di.bean.BeanInjection
import com.akhutornoy.petclinic.repository.Repository

object RepositoryInjection {

    fun provideRepository() = Repository(
        BeanInjection.provideRestService()
    )
}