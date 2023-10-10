package com.elanyudho.core.domain.usecase

import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonByNameUseCase @Inject constructor(private val repo: PokemonRepository) {
    suspend fun getPokemonByName(name: String, sort: String, page: Int): Flow<List<Pokemon>> {
        return repo.getPokemonByName(page, sort, name)
    }
}