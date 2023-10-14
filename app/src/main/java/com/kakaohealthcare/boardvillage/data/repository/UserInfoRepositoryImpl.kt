package com.kakaohealthcare.boardvillage.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoRepositoryImpl @Inject constructor(database: FirebaseDatabase) : UserInfoRepository {

    override suspend fun getUserInfo() {
        TODO("Not yet impemented")
    }

    override suspend fun setUserInfo() {
        TODO("Not yet implemented")
    }
}