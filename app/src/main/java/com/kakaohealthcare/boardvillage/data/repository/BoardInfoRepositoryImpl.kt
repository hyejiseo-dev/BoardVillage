package com.kakaohealthcare.boardvillage.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.kakaohealthcare.boardvillage.domain.model.Board
import com.kakaohealthcare.boardvillage.domain.repository.BoardInfoRepository
import javax.inject.Inject

class BoardInfoRepositoryImpl @Inject constructor(
    private val boardDB: FirebaseDatabase
): BoardInfoRepository{
    override suspend fun addBoard(title: String, content: String, img_url: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllBoard(): Result<List<Board>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBoard(boardId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBoard(
        boardId: String,
        title: String,
        content: String,
        img_url: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

}