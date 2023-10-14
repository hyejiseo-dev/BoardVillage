package com.kakaohealthcare.boardvillage.domain.model

import java.sql.Date

data class Reply(
    val board_id: Int,
    val content: String,
    val reg_dt: Date,
    val update_dt: Date,
    val writer_ldap: String,
    val writer_profile: String
)