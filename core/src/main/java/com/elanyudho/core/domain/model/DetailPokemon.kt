package com.elanyudho.core.domain.model

data class DetailPokemon(
    val title: String = "",
    val imgUrl: String = "",
    val abilities: List<String> = listOf()
)