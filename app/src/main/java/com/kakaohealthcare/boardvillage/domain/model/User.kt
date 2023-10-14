package com.kakaohealthcare.boardvillage.domain.model

import java.sql.Date

data class User(
    val chatterbox: Boolean,
    val coffee_maker: Boolean,
    val fcm_token: String,
    val ldap_id: String,
    val phone_number: String,
    val profile_img: String,
    val reg_dt: Date,
    val sns_key: String,
    val update_dt: Date
)

