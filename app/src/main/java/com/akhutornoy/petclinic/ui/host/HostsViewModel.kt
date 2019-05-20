package com.akhutornoy.petclinic.ui.host

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.interactor.HostsInteractor
import com.akhutornoy.petclinic.ui.base.BaseViewModel

class HostsViewModel(
    private val hostsInteractor: HostsInteractor
) : BaseViewModel() {

    private val _hosts = MutableLiveData<List<HostModel>>()
    val hosts: LiveData<List<HostModel>>
        get() = _hosts


    fun getHosts() {
        launchBackgroundJob {
            _hosts.postValue(hostsInteractor.getAllHosts())
        }
    }

}