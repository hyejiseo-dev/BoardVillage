package com.kakaohealthcare.boardvillage.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kakaohealthcare.boardvillage.base.BaseViewModel
import com.kakaohealthcare.boardvillage.domain.model.User
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import com.kakaohealthcare.boardvillage.domain.usecase.GetUserInfoUseCase
import com.kakaohealthcare.boardvillage.util.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    override fun setInitialState() = LoginContract.State(
        ldapId = "",
        loginSuccess = false
    )

    suspend fun getUserInfo(userId: String) {
        if (getLoginUser(userId) != null) {
            setEvent(LoginContract.Event.loginSuccess(loginSuccess = true))
        } else {
            setEvent(LoginContract.Event.loginSuccess(loginSuccess = false))
        }
    }

    override fun handleEvents(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.getLdapId -> {
                setState {
                    copy(ldapId = event.ldapId)
                }
            }

            is LoginContract.Event.loginSuccess -> {
                setState {
                    copy(loginSuccess = event.loginSuccess)
                }
            }

        }
    }

    suspend fun getLoginUser(userId: String): User? {
        return getUserInfoUseCase.execute(userId)
    }

}