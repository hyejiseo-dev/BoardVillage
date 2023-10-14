package com.kakaohealthcare.boardvillage.ui.viewmodel

import androidx.navigation.compose.rememberNavController
import com.kakaohealthcare.boardvillage.base.BaseViewModel

class ProfileViewModel() :
    BaseViewModel<ProfileContract.Event, ProfileContract.State, ProfileContract.Effect>() {

//    private val _state = MutableStateFlow(ProfileContract.State("","", false))
//    val state: StateFlow<ProfileContract.State> = _state


    fun setLdapID(ldapId: String) {
        if (ldapId != "") {
            setEvent(ProfileContract.Event.InputLdapId(ldapId = ldapId))
        }
    }

    fun setPhoneNumber(phoneNumber: String){
        if(phoneNumber != ""){
            setEvent(ProfileContract.Event.InputPhoneNumber(phoneNumber = phoneNumber))
        }
    }

    fun saveProfile(){
        if(!viewState.value.ldapId.equals("") || !viewState.value.phoneNumber.equals("")){
            setEvent(ProfileContract.Event.OnSaveProfile(saveProfile = true))
        } else {
            setEvent(ProfileContract.Event.OnSaveProfile(saveProfile = false))
        }
    }

    override fun setInitialState() = ProfileContract.State(
        ldapId = "",
        phoneNumber = "",
        saveProfile = false,
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

            is ProfileContract.Event.OnSaveProfile ->{
                setState {
                    copy(saveProfile = event.saveProfile)
                }
            }
        }
    }

}