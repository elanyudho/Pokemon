package com.elanyudho.core.data.remote.service

import com.elanyudho.core.data.remote.response.PokemonDetailResponse
import com.elanyudho.core.data.remote.response.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): Response<PokemonsResponse>

    @GET("pokemon/{id}/")
    suspend fun getDetailPokemon(
        @Path("id") id: String,
    ): Response<PokemonDetailResponse>
}