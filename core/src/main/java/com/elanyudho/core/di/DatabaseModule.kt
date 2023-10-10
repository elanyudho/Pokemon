package com.elanyudho.core.di

import android.content.Context
import com.elanyudho.core.data.local.db.DatabaseHelper
import com.elanyudho.core.data.local.db.PokemonHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseHelper(@ApplicationContext context: Context): DatabaseHelper {
        return DatabaseHelper(context)
    }

    @Singleton
    @Provides
    fun providePokemonHelper(databaseHelper: DatabaseHelper): PokemonHelper {
        return PokemonHelper(databaseHelper)
    }
}