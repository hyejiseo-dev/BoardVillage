package com.kakaohealthcare.boardvillage.domain.repository

import com.kakaohealthcare.boardvillage.domain.model.Board

interface BoardInfoRepository {
    suspend fun addBoard(title: String, content: String, img_url: String): Result<Unit>

    suspend fun getAllBoard(): Result<List<Board>>

    suspend fun deleteBoard(boardId: String): Result<Unit>

    suspend fun updateBoard(boardId: String, title: String, content: String, img_url: String) : Result<Unit>
}