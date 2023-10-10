package com.elanyudho.core.data.remote.source

import com.elanyudho.core.data.remote.response.PokemonDetailResponse
import com.elanyudho.core.data.remote.response.PokemonsResponse
import com.elanyudho.core.data.remote.service.ApiService
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.vo.Either
import javax.inject.Inject

class RemoteDataSource
@Inject constructor(private val api: ApiService) : RemoteSafeRequest() {

    suspend fun getPokemons(offset: String, limit: String): Either<Failure, PokemonsResponse> =
        request {
            api.getPokemons(offset, limit)
        }

    suspend fun getDetailPokemon(id: String): Either<Failure, PokemonDetailResponse> =
        request {
            api.getDetailPokemon(id)
        }
}