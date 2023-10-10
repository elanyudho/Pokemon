package com.elanyudho.pokemon.ui.search

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.GetPokemonByNameUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getPokemonByNameUseCase: GetPokemonByNameUseCase
) : BaseViewModel<SearchViewModel.SearchUiState>() {

    sealed class SearchUiState {
        object InitialLoading : SearchUiState()
        object PagingLoading : SearchUiState()
        data class PokemonsLoaded(val data: List<Pokemon>) : SearchUiState()

    }

    fun getPokemonByName(name: String, sort: String, page: Long) {
        viewModelScope.launch(dispatcherProvider.io) {
            getPokemonByNameUseCase.getPokemonByName(name, sort, page.toInt())
                .onStart {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = if (page == 1L) {
                            SearchUiState.InitialLoading
                        } else {
                            SearchUiState.PagingLoading
                        }
                    }
                }
                .collect { data ->
                    withContext(dispatcherProvider.main) {
                        _uiState.value = SearchUiState.PokemonsLoaded(data)
                    }
                }
        }
    }
}