package com.akhutornoy.petclinic.di.ui.host

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateVMFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.savedstate.SavedStateRegistryOwner
import com.akhutornoy.petclinic.di.interactor.PetsInteractorInjection
import com.akhutornoy.petclinic.interactor.PetsInteractor
import com.akhutornoy.petclinic.ui.base.BaseFragment
import com.akhutornoy.petclinic.ui.pet.PetsFragment
import com.akhutornoy.petclinic.ui.pet.PetsViewModel

object PetsUiInjection {

    fun providePetsViewModel(fragment: PetsFragment): PetsViewModel {
        return ViewModelProviders.of(fragment, provideViewModelFactory(fragment))
            .get(PetsViewModel::class.java)
    }

    fun providePetsViewModel(dialogFragment: PetsFragment.AddPetDialog): PetsViewModel {
        return providePetsViewModel(dialogFragment.parentFragment as PetsFragment)
    }

    private fun provideViewModelFactory(fragment: BaseFragment) =
        ViewModelFactory(
            fragment,
            fragment.arguments!!,
            PetsInteractorInjection.provideInteractor())

    @Suppress("UNCHECKED_CAST")
    private class ViewModelFactory(
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle,
        private val petsInteractor: PetsInteractor
    ) : AbstractSavedStateVMFactory(owner, defaultArgs) {

        override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
            return (when (modelClass) {
                PetsViewModel::class.java -> getPetsViewModel(handle)
                else -> throw IllegalArgumentException("Don't have ViewModel for '$modelClass'")
            }) as T
        }

        private fun getPetsViewModel(handle: SavedStateHandle): PetsViewModel {
            val hostId: Long = handle.get(PetsFragment.ARG_HOST_ID)!!
            return PetsViewModel(handle, hostId, petsInteractor)
        }

    }

}