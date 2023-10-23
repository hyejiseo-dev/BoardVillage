package com.kakaohealthcare.boardvillage.domain.usecase

import com.kakaohealthcare.boardvillage.domain.model.InvalidUserException
import com.kakaohealthcare.boardvillage.domain.model.User
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(val repo: UserInfoRepository) {
    suspend fun execute(userId : String): User? {
        return repo.getUserInfo(userId)
    }

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: User) {
        if(user.ldap_id.isBlank()) {
            throw InvalidUserException("The ldap_id of the note can't be empty.")
        }
        if(user.phone_number.isBlank()) {
            throw InvalidUserException("The phone_number of the note can't be empty.")
        }
        repo.setUserInfo(user)
    }
}