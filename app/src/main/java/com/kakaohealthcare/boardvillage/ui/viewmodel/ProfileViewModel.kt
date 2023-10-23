package com.kakaohealthcare.boardvillage.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kakaohealthcare.boardvillage.base.BaseViewModel
import com.kakaohealthcare.boardvillage.domain.model.User
import com.kakaohealthcare.boardvillage.domain.usecase.GetUserInfoUseCase
import com.kakaohealthcare.boardvillage.util.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {

    fun inputLdapId(ldapID: String) {
        setEvent(ProfileContract.Event.InputLdapId(ldapId = ldapID))
    }

    fun inputPhoneNumber(phoneNumber: String) {
        setEvent(ProfileContract.Event.InputPhoneNumber(phoneNumber = phoneNumber))
    }

    fun saveProfile() {
        if (viewState.value.ldapId != "" && viewState.value.phoneNumber != "") {
            setEvent(ProfileContract.Event.OnSaveProfile(saveProfile = true))
            setProfile()
        } else {
            setEvent(ProfileContract.Event.OnSaveProfile(saveProfile = false))
        }
    }

    override fun setInitialState() = ProfileContract.State(
        ldapId = "",
        phoneNumber = "",
        profileImg = "",
        saveProfile = false
    )

    override fun handleEvents(event: ProfileContract.Event) {
        when (event) {
            is ProfileContract.Event.InputLdapId -> {
                setState {
                    copy(ldapId = event.ldapId)
                }
            }

            is ProfileContract.Event.InputPhoneNumber -> {
                setState {
                    copy(phoneNumber = event.phoneNumber)
                }
            }

            is ProfileContract.Event.InputProfileImage -> {
                setState {
                    copy(profileImg = event.profileImg)
                }
            }

            is ProfileContract.Event.OnSaveProfile -> {
                setState {
                    copy(saveProfile = event.saveProfile)
                }
                if (!event.saveProfile) {
                    setEffect { ProfileContract.Effect.ShowSnackBar }
                }
            }

        }
    }

    private fun setProfile() {
        viewModelScope.launch {
            getUserInfoUseCase.invoke(
                user = User(
                    chatterbox = false,
                    coffee_maker = false,
                    fcm_token = "",
                    ldap_id = viewState.value.ldapId,
                    phone_number = viewState.value.phoneNumber,
                    profile_img = viewState.value.profileImg,
                    reg_dt = getCurrentTime(),
                    update_dt = getCurrentTime()
                )
            )
        }
    }

}


