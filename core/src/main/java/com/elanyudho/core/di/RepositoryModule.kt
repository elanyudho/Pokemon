package com.elanyudho.core.di

import com.elanyudho.core.data.repository.PokemonRepositoryImpl
import com.elanyudho.core.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    @ActivityScoped
    abstract fun bindToolRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository

}