package com.akhutornoy.petclinic.interactor

import com.akhutornoy.petclinic.entity.mapper.PetModelMapper
import com.akhutornoy.petclinic.entity.mapper.PetRequestMapper
import com.akhutornoy.petclinic.entity.ui.PetModel
import com.akhutornoy.petclinic.repository.Repository

class PetsInteractor(
    private val repository: Repository,
    private val petModelMapper: PetModelMapper,
    private val petRequestMapper: PetRequestMapper
) {

    fun getAllPets(hostId: Long): List<PetModel> {
        return repository
            .getAllPets(hostId)
            .map(petModelMapper::map)
    }

    fun addPet(name: String, breed: String, hostId: Long) {
        repository.addPet(petRequestMapper.map(name, breed, hostId))
    }

    fun deletePet(petId: Long) {
        repository.deletePet(petId)
    }

}