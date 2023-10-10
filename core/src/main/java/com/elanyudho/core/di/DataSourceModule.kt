package com.elanyudho.core.di


import com.elanyudho.core.data.local.LocalDataSource
import com.elanyudho.core.data.local.db.PokemonHelper
import com.elanyudho.core.data.remote.service.ApiService
import com.elanyudho.core.data.remote.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object  DataSourceModule {

    @Provides
    @ActivityScoped
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Provides
    @ActivityScoped
    fun provideLocalDataSource(pokemonHelper: PokemonHelper) = LocalDataSource(pokemonHelper)
}