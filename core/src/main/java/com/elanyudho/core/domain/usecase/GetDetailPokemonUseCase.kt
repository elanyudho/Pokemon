package com.elanyudho.core.domain.usecase

import com.elanyudho.core.abstraction.UseCase
import com.elanyudho.core.domain.model.DetailPokemon
import com.elanyudho.core.domain.repository.PokemonRepository
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.vo.Either
import javax.inject.Inject

class GetDetailPokemonUseCase @Inject constructor(private val repo: PokemonRepository): UseCase<DetailPokemon, String>(){

    override suspend fun run(params: String): Either<Failure, DetailPokemon> {
        return repo.getDetailPokemon(params)
    }
}