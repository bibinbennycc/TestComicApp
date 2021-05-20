package com.codetest.comicsapp.di

import com.codetest.comicsapp.feature.repository.AppRepository
import com.codetest.comicsapp.feature.repository.ComicRepository
import com.codetest.comicsapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesAppRepository(apiService: ApiService):AppRepository{
        return ComicRepository(apiService)
    }
}