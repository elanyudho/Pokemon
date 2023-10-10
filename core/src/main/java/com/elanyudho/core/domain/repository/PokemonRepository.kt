package com.elanyudho.core.domain.repository

import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.core.domain.model.DetailPokemon
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.vo.Either
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(page: Int): Either<Failure, Flow<List<Pokemon>>>

    suspend fun getPokemonByName(page: Int, sort: String, name: String): Flow<List<Pokemon>>

    suspend fun getDetailPokemon(id: String): Either<Failure, DetailPokemon>
}