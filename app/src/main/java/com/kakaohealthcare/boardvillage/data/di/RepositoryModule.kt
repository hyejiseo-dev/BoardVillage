package com.kakaohealthcare.boardvillage.data.di

import com.google.firebase.database.FirebaseDatabase
import com.kakaohealthcare.boardvillage.data.repository.UserInfoRepositoryImpl
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserInfoRepository(database: FirebaseDatabase): UserInfoRepository {
        return UserInfoRepositoryImpl(database)
    }
}