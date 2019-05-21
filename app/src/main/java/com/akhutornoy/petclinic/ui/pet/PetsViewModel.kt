package com.akhutornoy.petclinic.ui.pet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.akhutornoy.petclinic.entity.ui.PetModel
import com.akhutornoy.petclinic.interactor.PetsInteractor
import com.akhutornoy.petclinic.ui.base.BaseViewModel

class PetsViewModel(
    private val state: SavedStateHandle,
    private val hostId: Long,
    private val petsInteractor: PetsInteractor
) : BaseViewModel() {

    private val _pets = MutableLiveData<List<PetModel>>()
    val pets: LiveData<List<PetModel>>
        get() = _pets

    fun getPets() {
        launchBackgroundJob {
            _pets.postValue(petsInteractor.getAllPets(hostId))
        }
    }

    fun addPet(name: String, breed: String) {
        launchBackgroundJob {
            petsInteractor.addPet(name, breed, hostId)
            _pets.postValue(petsInteractor.getAllPets(hostId))
        }
    }

    fun deletePet(petId: Long) {
        launchBackgroundJob {
            petsInteractor.deletePet(petId)
            _pets.postValue(petsInteractor.getAllPets(hostId))
        }
    }

}