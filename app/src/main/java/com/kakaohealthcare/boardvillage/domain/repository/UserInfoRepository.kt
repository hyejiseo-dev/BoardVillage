package com.kakaohealthcare.boardvillage.domain.repository

interface UserInfoRepository {

    suspend fun getUserInfo()

    suspend fun setUserInfo()
}