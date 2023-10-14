package com.kakaohealthcare.boardvillage.domain.model

data class Board(
    val content: String,
    val img_url: String,
    val reg_dt: Int,
    val title: String,
    val update_dt: Int,
    val writer_ldap: String,
    val writer_profile: String
)


object DataProvider {
    val boardList = listOf(
        Board("내용 입니다1", "", 1, "title1",2, "elin", "writer"),
        Board("내용 입니다2", "", 1, "title2",2, "annie", "writer")
    )
}