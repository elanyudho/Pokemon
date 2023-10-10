package com.elanyudho.core.di

import com.elanyudho.core.domain.repository.PokemonRepository
import com.elanyudho.core.domain.usecase.GetDetailPokemonUseCase
import com.elanyudho.core.domain.usecase.GetPokemonByNameUseCase
import com.elanyudho.core.domain.usecase.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {
    @Provides
    @ActivityScoped
    fun provideGetPokemonsUseCase(repository: PokemonRepository) = GetPokemonsUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideGetPokemonsByNameUseCase(repository: PokemonRepository) = GetPokemonByNameUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideGetDetailPokemonUseCase(repository: PokemonRepository) = GetDetailPokemonUseCase(repository)
}

