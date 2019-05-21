package com.akhutornoy.petclinic.di.ui.host

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.akhutornoy.petclinic.di.interactor.HostsInteractorInjection
import com.akhutornoy.petclinic.interactor.HostsInteractor
import com.akhutornoy.petclinic.ui.host.HostsFragment
import com.akhutornoy.petclinic.ui.host.HostsViewModel

object HostsUiInjection {

    fun provideHostsViewModel(fragment: HostsFragment): HostsViewModel {
        return ViewModelProviders.of(fragment, provideViewModelFactory())
            .get(HostsViewModel::class.java)
    }

    fun provideHostsViewModel(dialogFragment: HostsFragment.AddHostDialog): HostsViewModel {
        return provideHostsViewModel(dialogFragment.parentFragment as HostsFragment)
    }

    private fun provideViewModelFactory() =
        ViewModelFactory(HostsInteractorInjection.provideInteractor())

    @Suppress("UNCHECKED_CAST")
    private class ViewModelFactory(
        private val hostsInteractor: HostsInteractor
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return (when (modelClass) {
                HostsViewModel::class.java -> HostsViewModel(hostsInteractor)
                else -> throw IllegalArgumentException("Don't have ViewModel for '$modelClass'")
            }) as T
        }

    }

}