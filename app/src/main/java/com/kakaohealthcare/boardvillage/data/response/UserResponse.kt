package com.kakaohealthcare.boardvillage.data.response

import kotlinx.serialization.Serializable
import java.sql.Date

@Serializable
data class UserResponse(
    val chatterbox: Boolean,
    val coffee_maker: Boolean,
    val fcm_token: String,
    val ldap_id: String,
    val phone_number: String,
    val profile_img: String,
    val reg_dt: Date,
    val sns_key: String,
)
