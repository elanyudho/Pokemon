package com.elanyudho.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("abilities")
    val abilities: List<Ability?>? = listOf(),
    @SerializedName("base_experience")
    val baseExperience: Int? = 0,
    @SerializedName("forms")
    val forms: List<Form?>? = listOf(),
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice?>? = listOf(),
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("held_items")
    val heldItems: List<Any?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("is_default")
    val isDefault: Boolean? = false,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String? = "",
    @SerializedName("moves")
    val moves: List<Move?>? = listOf(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("order")
    val order: Int? = 0,
    @SerializedName("past_types")
    val pastTypes: List<Any?>? = listOf(),
    @SerializedName("species")
    val species: Species? = Species(),
    @SerializedName("sprites")
    val sprites: Sprites? = Sprites(),
    @SerializedName("stats")
    val stats: List<Stat?>? = listOf(),
    @SerializedName("types")
    val types: List<Type?>? = listOf(),
    @SerializedName("weight")
    val weight: Int? = 0
) {
    data class Ability(
        @SerializedName("ability")
        val ability: Ability? = Ability(),
        @SerializedName("is_hidden")
        val isHidden: Boolean? = false,
        @SerializedName("slot")
        val slot: Int? = 0
    ) {
        data class Ability(
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("url")
            val url: String? = ""
        )
    }

    data class Form(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("url")
        val url: String? = ""
    )

    data class GameIndice(
        @SerializedName("game_index")
        val gameIndex: Int? = 0,
        @SerializedName("version")
        val version: Version? = Version()
    ) {
        data class Version(
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("url")
            val url: String? = ""
        )
    }

    data class Move(
        @SerializedName("move")
        val move: Move? = Move(),
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail?>? = listOf()
    ) {
        data class Move(
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("url")
            val url: String? = ""
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int? = 0,
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod? = MoveLearnMethod(),
            @SerializedName("version_group")
            val versionGroup: VersionGroup? = VersionGroup()
        ) {
            data class MoveLearnMethod(
                @SerializedName("name")
                val name: String? = "",
                @SerializedName("url")
                val url: String? = ""
            )

            data class VersionGroup(
                @SerializedName("name")
                val name: String? = "",
                @SerializedName("url")
                val url: String? = ""
            )
        }
    }

    data class Species(
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("url")
        val url: String? = ""
    )

    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String? = "",
        @SerializedName("back_female")
        val backFemale: Any? = Any(),
        @SerializedName("back_shiny")
        val backShiny: String? = "",
        @SerializedName("back_shiny_female")
        val backShinyFemale: Any? = Any(),
        @SerializedName("front_default")
        val frontDefault: String? = "",
        @SerializedName("front_female")
        val frontFemale: Any? = Any(),
        @SerializedName("front_shiny")
        val frontShiny: String? = "",
        @SerializedName("front_shiny_female")
        val frontShinyFemale: Any? = Any(),
        @SerializedName("other")
        val other: Other? = Other(),
        @SerializedName("versions")
        val versions: Versions? = Versions()
    ) {
        data class Other(
            @SerializedName("dream_world")
            val dreamWorld: DreamWorld? = DreamWorld(),
            @SerializedName("home")
            val home: Home? = Home(),
            @SerializedName("official-artwork")
            val officialArtwork: OfficialArtwork? = OfficialArtwork()
        ) {
            data class DreamWorld(
                @SerializedName("front_default")
                val frontDefault: String? = "",
                @SerializedName("front_female")
                val frontFemale: Any? = Any()
            )

            data class Home(
                @SerializedName("front_default")
                val frontDefault: String? = "",
                @SerializedName("front_female")
                val frontFemale: Any? = Any(),
                @SerializedName("front_shiny")
                val frontShiny: String? = "",
                @SerializedName("front_shiny_female")
                val frontShinyFemale: Any? = Any()
            )

            data class OfficialArtwork(
                @SerializedName("front_default")
                val frontDefault: String? = "",
                @SerializedName("front_shiny")
                val frontShiny: String? = ""
            )
        }

        data class Versions(
            @SerializedName("generation-i")
            val generationI: GenerationI? = GenerationI(),
            @SerializedName("generation-ii")
            val generationIi: GenerationIi? = GenerationIi(),
            @SerializedName("generation-iii")
            val generationIii: GenerationIii? = GenerationIii(),
            @SerializedName("generation-iv")
            val generationIv: GenerationIv? = GenerationIv(),
            @SerializedName("generation-v")
            val generationV: GenerationV? = GenerationV(),
            @SerializedName("generation-vi")
            val generationVi: GenerationVi? = GenerationVi(),
            @SerializedName("generation-vii")
            val generationVii: GenerationVii? = GenerationVii(),
            @SerializedName("generation-viii")
            val generationViii: GenerationViii? = GenerationViii()
        ) {
            data class GenerationI(
                @SerializedName("red-blue")
                val redBlue: RedBlue? = RedBlue(),
                @SerializedName("yellow")
                val yellow: Yellow? = Yellow()
            ) {
                data class RedBlue(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_gray")
                    val backGray: String? = "",
                    @SerializedName("back_transparent")
                    val backTransparent: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_gray")
                    val frontGray: String? = "",
                    @SerializedName("front_transparent")
                    val frontTransparent: String? = ""
                )

                data class Yellow(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_gray")
                    val backGray: String? = "",
                    @SerializedName("back_transparent")
                    val backTransparent: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_gray")
                    val frontGray: String? = "",
                    @SerializedName("front_transparent")
                    val frontTransparent: String? = ""
                )
            }

            data class GenerationIi(
                @SerializedName("crystal")
                val crystal: Crystal? = Crystal(),
                @SerializedName("gold")
                val gold: Gold? = Gold(),
                @SerializedName("silver")
                val silver: Silver? = Silver()
            ) {
                data class Crystal(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("back_shiny_transparent")
                    val backShinyTransparent: String? = "",
                    @SerializedName("back_transparent")
                    val backTransparent: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_transparent")
                    val frontShinyTransparent: String? = "",
                    @SerializedName("front_transparent")
                    val frontTransparent: String? = ""
                )

                data class Gold(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_transparent")
                    val frontTransparent: String? = ""
                )

                data class Silver(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_transparent")
                    val frontTransparent: String? = ""
                )
            }

            data class GenerationIii(
                @SerializedName("emerald")
                val emerald: Emerald? = Emerald(),
                @SerializedName("firered-leafgreen")
                val fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
                @SerializedName("ruby-sapphire")
                val rubySapphire: RubySapphire? = RubySapphire()
            ) {
                data class Emerald(
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    val frontShiny: String? = ""
                )

                data class FireredLeafgreen(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    val frontShiny: String? = ""
                )

                data class RubySapphire(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_shiny")
                    val frontShiny: String? = ""
                )
            }

            data class GenerationIv(
                @SerializedName("diamond-pearl")
                val diamondPearl: DiamondPearl? = DiamondPearl(),
                @SerializedName("heartgold-soulsilver")
                val heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
                @SerializedName("platinum")
                val platinum: Platinum? = Platinum()
            ) {
                data class DiamondPearl(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_female")
                    val backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                )

                data class HeartgoldSoulsilver(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_female")
                    val backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                )

                data class Platinum(
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_female")
                    val backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                )
            }

            data class GenerationV(
                @SerializedName("black-white")
                val blackWhite: BlackWhite? = BlackWhite()
            ) {
                data class BlackWhite(
                    @SerializedName("animated")
                    val animated: Animated? = Animated(),
                    @SerializedName("back_default")
                    val backDefault: String? = "",
                    @SerializedName("back_female")
                    val backFemale: Any? = Any(),
                    @SerializedName("back_shiny")
                    val backShiny: String? = "",
                    @SerializedName("back_shiny_female")
                    val backShinyFemale: Any? = Any(),
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                ) {
                    data class Animated(
                        @SerializedName("back_default")
                        val backDefault: String? = "",
                        @SerializedName("back_female")
                        val backFemale: Any? = Any(),
                        @SerializedName("back_shiny")
                        val backShiny: String? = "",
                        @SerializedName("back_shiny_female")
                        val backShinyFemale: Any? = Any(),
                        @SerializedName("front_default")
                        val frontDefault: String? = "",
                        @SerializedName("front_female")
                        val frontFemale: Any? = Any(),
                        @SerializedName("front_shiny")
                        val frontShiny: String? = "",
                        @SerializedName("front_shiny_female")
                        val frontShinyFemale: Any? = Any()
                    )
                }
            }

            data class GenerationVi(
                @SerializedName("omegaruby-alphasapphire")
                val omegarubyAlphasapphire: OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
                @SerializedName("x-y")
                val xY: XY? = XY()
            ) {
                data class OmegarubyAlphasapphire(
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                )

                data class XY(
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                )
            }

            data class GenerationVii(
                @SerializedName("icons")
                val icons: Icons? = Icons(),
                @SerializedName("ultra-sun-ultra-moon")
                val ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any()
                )

                data class UltraSunUltraMoon(
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any(),
                    @SerializedName("front_shiny")
                    val frontShiny: String? = "",
                    @SerializedName("front_shiny_female")
                    val frontShinyFemale: Any? = Any()
                )
            }

            data class GenerationViii(
                @SerializedName("icons")
                val icons: Icons? = Icons()
            ) {
                data class Icons(
                    @SerializedName("front_default")
                    val frontDefault: String? = "",
                    @SerializedName("front_female")
                    val frontFemale: Any? = Any()
                )
            }
        }
    }

    data class Stat(
        @SerializedName("base_stat")
        val baseStat: Int? = 0,
        @SerializedName("effort")
        val effort: Int? = 0,
        @SerializedName("stat")
        val stat: Stat? = Stat()
    ) {
        data class Stat(
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("url")
            val url: String? = ""
        )
    }

    data class Type(
        @SerializedName("slot")
        val slot: Int? = 0,
        @SerializedName("type")
        val type: Type? = Type()
    ) {
        data class Type(
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("url")
            val url: String? = ""
        )
    }
}