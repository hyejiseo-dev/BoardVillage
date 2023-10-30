package com.kakaohealthcare.boardvillage.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kakaohealthcare.boardvillage.base.BaseViewModel
import com.kakaohealthcare.boardvillage.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    init { getUsers("annie_ble") }

    override fun setInitialState() = LoginContract.State(
        ldapId = "",
        loginSuccess = false,
        user = null
    )

//    suspend fun getUserInfo(userId: String) {
//        if (getLoginUser(userId) != null) {
//            setEvent(LoginContract.Event.loginSuccess(loginSuccess = true))
//        } else {
//            setEvent(LoginContract.Event.loginSuccess(loginSuccess = false))
//        }
//    }

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.getLdapId -> {
                setState {
                    copy(ldapId = event.ldapId)
                }
            }

            is LoginContract.Event.getLoginUser -> {
                setState {
                    copy(user = event.user)
                }
            }

            is LoginContract.Event.loginSuccess -> {
                setState {
                    copy(loginSuccess = event.loginSuccess)
                }
            }

        }
    }

//    suspend fun getLoginUser(userId: String) {
//        return getUserInfoUseCase.execute(userId)
//    }


    private fun getUsers(userId: String) {
        viewModelScope.launch {
            setState { copy(loginSuccess = true) }

            getUserInfoUseCase.execute(userId = userId)
                .onSuccess { user ->
                    setState { copy(loginSuccess = true, user = user) }
                    setEvent(LoginContract.Event.loginSuccess(loginSuccess = true))
                }
                .onFailure {
                    setState { copy(loginSuccess = true, user = user) }
                    setEvent(LoginContract.Event.loginSuccess(loginSuccess = true))
                }
        }
    }

}