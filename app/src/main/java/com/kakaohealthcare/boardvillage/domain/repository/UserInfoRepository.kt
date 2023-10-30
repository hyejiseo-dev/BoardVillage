package com.kakaohealthcare.boardvillage.domain.repository

import com.kakaohealthcare.boardvillage.domain.model.User
import java.sql.Date

interface UserInfoRepository {

    suspend fun getUserInfo(userId: String): Result<User>

    suspend fun setUserInfo(user: User)
}