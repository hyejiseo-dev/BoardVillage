package com.kakaohealthcare.boardvillage.domain.model

import java.sql.Date


data class Board(
    val content: String,
    val img_url: String,
    val reg_dt: Date,
    val title: String,
    val update_dt: Date,
    val writer_ldap: String,
    val writer_profile: String
)


//object DataProvider {
//    val boardList = listOf(
//        Board("내용 입니다1", "", 1, "title1",2, "annie", "writer"),
//        Board("내용 입니다2", "", 1, "title2",2, "annie", "writer")
//    )
//}