package com.kakaohealthcare.boardvillage.domain.model

import java.sql.Date

data class Events(
    val img_url: String,
    val reg_dt: Date,
    val title: String
)