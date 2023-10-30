package com.kakaohealthcare.boardvillage.data.di

import com.google.firebase.database.FirebaseDatabase
import com.kakaohealthcare.boardvillage.data.repository.BoardInfoRepositoryImpl
import com.kakaohealthcare.boardvillage.data.repository.UserInfoRepositoryImpl
import com.kakaohealthcare.boardvillage.domain.repository.BoardInfoRepository
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserInfoRepository(database: FirebaseDatabase,
                                   @IoDispatcher ioDispatcher: CoroutineDispatcher): UserInfoRepository {
        return UserInfoRepositoryImpl(database, ioDispatcher)
    }

    @Provides
    @Singleton
    fun providesBoardInfoRepository(
        database: FirebaseDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): BoardInfoRepository {
        return BoardInfoRepositoryImpl(database)
    }
}