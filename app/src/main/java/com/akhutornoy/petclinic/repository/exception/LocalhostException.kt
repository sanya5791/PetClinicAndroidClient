package com.akhutornoy.petclinic.repository.exception

import android.accounts.NetworkErrorException
import retrofit2.Response

class LocalhostException(response: Response<*>) : NetworkErrorException(
    "Network Error code=${response.code()}, " +
        "message=${response.message()}")