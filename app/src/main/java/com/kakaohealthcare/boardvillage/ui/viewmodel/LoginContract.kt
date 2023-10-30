package com.kakaohealthcare.boardvillage.ui.viewmodel

import com.google.errorprone.annotations.Immutable
import com.kakaohealthcare.boardvillage.base.ViewEffect
import com.kakaohealthcare.boardvillage.base.ViewEvent
import com.kakaohealthcare.boardvillage.base.ViewState
import com.kakaohealthcare.boardvillage.domain.model.User

class LoginContract {

    sealed class Event : ViewEvent {
        data class getLdapId(val ldapId : String) : Event()
        data class loginSuccess(val loginSuccess: Boolean) : Event()
        data class getLoginUser(val user: User) : Event()
    }

    @Immutable
    data class State(
        var ldapId: String,
        var loginSuccess: Boolean,
        var user: User?
    ) : ViewState

    sealed class Effect : ViewEffect {
    }
}