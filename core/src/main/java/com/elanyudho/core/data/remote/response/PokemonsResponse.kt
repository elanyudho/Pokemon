package com.elanyudho.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("previous")
    val previous: String? = "",
    @SerializedName("results")
    val results: List<Result?>? = listOf()
) {
    data class Result(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("url")
        val url: String? = ""
    )
}