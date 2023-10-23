package com.kakaohealthcare.boardvillage.domain.model

import java.sql.Date

data class User(
    val chatterbox: Boolean = false,
    val coffee_maker: Boolean = false,
    val fcm_token: String = "",
    val ldap_id: String = "",
    val phone_number: String = "",
    val profile_img: String = "",
    val reg_dt: String = "",
    val update_dt: String = ""
)

class InvalidUserException(message: String): Exception(message)

