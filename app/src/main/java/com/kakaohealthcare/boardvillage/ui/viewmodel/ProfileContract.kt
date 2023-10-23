package com.kakaohealthcare.boardvillage.ui.viewmodel

import com.google.errorprone.annotations.Immutable
import com.kakaohealthcare.boardvillage.base.ViewEffect
import com.kakaohealthcare.boardvillage.base.ViewEvent
import com.kakaohealthcare.boardvillage.base.ViewState

class ProfileContract {

    sealed class Event : ViewEvent {
        data class InputLdapId(val ldapId: String) : Event()
        data class InputPhoneNumber(val phoneNumber: String) : Event()
        data class InputProfileImage(val profileImg: String) : Event()
        data class OnSaveProfile(val saveProfile: Boolean) : Event()

    }

    @Immutable
    data class State(
        var ldapId: String = "",
        var phoneNumber: String = "",
        var profileImg : String = "",
        var saveProfile: Boolean = false
    ) : ViewState

    sealed class Effect : ViewEffect {

        object ShowSnackBar : Effect()

    }
}