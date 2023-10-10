package com.elanyudho.core.domain.usecase

import com.elanyudho.core.abstraction.UseCase
import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.core.domain.repository.PokemonRepository
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.vo.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repo: PokemonRepository) : UseCase<Flow<List<Pokemon>>, GetPokemonsUseCase.Params>() {

    data class Params(
        val page: Int
    )

    override suspend fun run(params: Params): Either<Failure, Flow<List<Pokemon>>> {
        return repo.getPokemons(params.page)
    }
}