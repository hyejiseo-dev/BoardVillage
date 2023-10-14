package com.kakaohealthcare.boardvillage.ui.viewmodel

import androidx.compose.runtime.MutableState
import com.kakaohealthcare.boardvillage.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel() :
    BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    private val _state = MutableStateFlow(HomeContract.State("",false))
    val state : StateFlow<HomeContract.State> = _state

//    private val _state = MutableStateFlow(ProfileContract.State("","", false))
//    val state: StateFlow<ProfileContract.State> = _state


//    fun LoginSuccess() {
//        if (viewState.value.loginSuccess) {
//            setEvent(HomeContract.Event.KakaoLoginSuccess(loginSuccess = true))
//        } else {
//            setEvent(HomeContract.Event.KakaoLoginSuccess(loginSuccess = false))
//        }
//    }

    override fun setInitialState() = HomeContract.State(
        loginToken = "",
        loginSuccess = false
    )

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.getLoginToken -> {
                setState {
                    copy(loginToken = event.loginToken)
                }
            }

            is HomeContract.Event.kakaoLoginSuccess -> {
                setState {
                    copy(loginSuccess = event.loginSuccess)
                }
            }

            else -> {}
        }
    }

}