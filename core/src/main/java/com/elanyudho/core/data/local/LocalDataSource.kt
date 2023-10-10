package com.elanyudho.core.data.local

import com.elanyudho.core.data.local.db.PokemonHelper
import com.elanyudho.core.data.local.entity.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val pokemonHelper: PokemonHelper) {

    suspend fun getPokemons(offset: Int, pageSize: Int): Flow<List<Pokemon>> = pokemonHelper.getPokemons(offset, pageSize)

    suspend fun getPokemonByName(name: String, sort: String, offset: Int, pageSize: Int): Flow<List<Pokemon>> = pokemonHelper.getPokemonByName(name, sort, offset, pageSize)

    suspend fun insertPokemon(pokemon: Pokemon) = pokemonHelper.insertPokemon(pokemon)
}