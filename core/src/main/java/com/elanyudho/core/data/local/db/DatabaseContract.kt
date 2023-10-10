package com.elanyudho.core.data.local.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class PokemonColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "pokemon"
            const val NAME = "name"
            const val URL = "url"
        }

    }
}