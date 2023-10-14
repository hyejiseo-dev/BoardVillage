package com.kakaohealthcare.boardvillage.domain.usecase

import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(val repo: UserInfoRepository) {
    suspend fun execute() {
        repo.getUserInfo()
    }
}