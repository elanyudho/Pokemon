package com.elanyudho.core.di

import com.elanyudho.core.data.remote.mapper.PokemonDetailMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MapperModule {

    @Provides
    @ActivityScoped
    fun providePokemonDetailMapper() = PokemonDetailMapper()
}