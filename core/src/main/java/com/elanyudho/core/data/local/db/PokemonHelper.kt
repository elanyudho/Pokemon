package com.elanyudho.core.data.local.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.elanyudho.core.data.local.db.DatabaseContract.PokemonColumns.Companion.NAME
import com.elanyudho.core.data.local.db.DatabaseContract.PokemonColumns.Companion.TABLE_NAME
import com.elanyudho.core.data.local.db.DatabaseContract.PokemonColumns.Companion.URL
import com.elanyudho.core.data.local.entity.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonHelper @Inject constructor(private val databaseHelper: DatabaseHelper) {

    fun insertPokemon(pokemon: Pokemon): Long {
        val db = databaseHelper.writableDatabase
        val values = ContentValues()
        values.put(NAME, pokemon.name)
        values.put(URL, pokemon.url)
        return db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE)
    }

    @SuppressLint("Range")
    fun getPokemons(offset: Int, pageSize: Int): Flow<List<Pokemon>> = flow {
        val pokemons = mutableListOf<Pokemon>()
        val db = databaseHelper.readableDatabase
        var cursor: Cursor? = null

        try {
            val query = "SELECT * FROM $TABLE_NAME LIMIT $pageSize OFFSET $offset"
            cursor = db.rawQuery(query, null)

            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(NAME))
                val url = cursor.getString(cursor.getColumnIndex(URL))
                pokemons.add(Pokemon(name, url))
            }

        } catch (e: SQLException) {
            // Handle exceptions if necessary
        } finally {
            cursor?.close()
        }
        emit(pokemons)
    }

    @SuppressLint("Range")
    fun getPokemonByName(name: String, sort: String, offset: Int, pageSize: Int): Flow<List<Pokemon>> = flow {
        val pokemons = mutableListOf<Pokemon>()

        withContext(Dispatchers.IO) {
            val db = databaseHelper.readableDatabase
            val sorter = "$NAME $sort"
            var cursor: Cursor? = null

            try {
                val query = "SELECT * FROM $TABLE_NAME WHERE $NAME LIKE '%$name%' ORDER BY $sorter LIMIT $pageSize OFFSET $offset"
                cursor = db.rawQuery(query, null)

                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndex(NAME))
                    val url = cursor.getString(cursor.getColumnIndex(URL))
                    pokemons.add(Pokemon(name, url))
                }

            } catch (e: SQLException) {
                // Handle exceptions if necessary
            } finally {
                cursor?.close()
            }
        }
        emit(pokemons)
    }

    fun closeDb() {
        databaseHelper.close()
    }
}