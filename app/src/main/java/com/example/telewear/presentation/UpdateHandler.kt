package com.example.telewear.presentation

import org.drinkless.td.libcore.telegram.Client
import org.drinkless.td.libcore.telegram.TdApi

class UpdateHandler : Client.ResultHandler {

    override fun onResult(obj: TdApi.Object?) {
        if (obj == null) {
            return
        }

        when (obj.constructor) {
            TdApi.UpdateAuthorizationState.CONSTRUCTOR -> onAuthStateUpdated()
        }
    }

    fun onAuthStateUpdated(authState : TdApi.AuthorizationState?){
    }
}

