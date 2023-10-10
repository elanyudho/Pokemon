package com.elanyudho.core.data.remote.mapper

import com.elanyudho.core.abstraction.BaseMapper
import com.elanyudho.core.data.remote.response.PokemonDetailResponse
import com.elanyudho.core.domain.model.DetailPokemon

class PokemonDetailMapper : BaseMapper<PokemonDetailResponse, DetailPokemon> {

    override fun mapToDomain(raw: PokemonDetailResponse): DetailPokemon {
        val abilities = ArrayList<String>()
        for (i in raw.abilities?: emptyList()) {
            abilities.add(i?.ability?.name?: "unknown")
        }
        return DetailPokemon(
            raw.name.toString(),
            raw.sprites?.frontDefault ?: "",
            abilities,
        )
    }

    override fun mapToRaw(domain: DetailPokemon): PokemonDetailResponse {
        return PokemonDetailResponse()
    }


}