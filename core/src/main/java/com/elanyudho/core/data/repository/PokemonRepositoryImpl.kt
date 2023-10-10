package com.elanyudho.core.data.repository

import com.elanyudho.core.data.local.LocalDataSource
import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.core.data.remote.mapper.PokemonDetailMapper
import com.elanyudho.core.data.remote.source.RemoteDataSource
import com.elanyudho.core.domain.model.DetailPokemon
import com.elanyudho.core.domain.repository.PokemonRepository
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.pagination.PagingConstant
import com.elanyudho.core.util.vo.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val pokemonDetailMapper: PokemonDetailMapper
) : PokemonRepository {
    override suspend fun getPokemons(page: Int): Either<Failure, Flow<List<Pokemon>>> {
        val offset = (page - 1) * PagingConstant.BATCH_SIZE

        return when (val response = remoteDataSource.getPokemons(offset.toString(), PagingConstant.BATCH_SIZE.toString())) {
            is Either.Success -> {
                val pokemonResponse = response.body
                pokemonResponse.results?.map { pokemon ->
                    localDataSource.insertPokemon(Pokemon(pokemon?.name?: "", pokemon?.url?: ""))
                }
                Either.Success(localDataSource.getPokemons(offset, PagingConstant.BATCH_SIZE))
            }

            is Either.Error -> {
                Either.Error(response.failure)
            }
        }

    }

    override suspend fun getPokemonByName(
        page: Int,
        sort: String,
        name: String
    ): Flow<List<Pokemon>> {
        val offset = (page - 1) * PagingConstant.BATCH_SIZE
        return localDataSource.getPokemonByName(name, sort, offset, PagingConstant.BATCH_SIZE)
    }

    override suspend fun getDetailPokemon(id: String): Either<Failure, DetailPokemon> {
        return when (val response = remoteDataSource.getDetailPokemon(id)) {
            is Either.Success -> {
                val data = pokemonDetailMapper.mapToDomain(response.body)
                Either.Success(data)
            }

            is Either.Error -> {
                Either.Error(response.failure)
            }
        }
    }
}