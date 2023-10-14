package com.kakaohealthcare.boardvillage.domain.di

import com.google.firebase.database.FirebaseDatabase
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import com.kakaohealthcare.boardvillage.domain.usecase.GetUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    // region AppInfo
    @Provides
    @Singleton
    fun provideGetUserInfoUseCase(repository: UserInfoRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(repository)
    }


}