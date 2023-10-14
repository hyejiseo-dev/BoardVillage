package com.kakaohealthcare.boardvillage.ui.viewmodel

import com.google.errorprone.annotations.Immutable
import com.kakaohealthcare.boardvillage.base.ViewEffect
import com.kakaohealthcare.boardvillage.base.ViewEvent
import com.kakaohealthcare.boardvillage.base.ViewState

class HomeContract {

    sealed class Event : ViewEvent {
        data class getLoginToken(val loginToken: String) : Event()
        data class kakaoLoginSuccess(val loginSuccess: Boolean) : Event()
    }

    @Immutable
    data class State(
        var loginToken: String,
        var loginSuccess: Boolean
    ) : ViewState{
    }

    sealed class Effect : ViewEffect {
        object ShowSnackBar : Effect()
    }
}