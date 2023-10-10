package com.elanyudho.pokemon.ui.main

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.data.local.entity.Pokemon
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.usecase.GetPokemonByNameUseCase
import com.elanyudho.core.domain.usecase.GetPokemonsUseCase
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.extension.onError
import com.elanyudho.core.util.extension.onSuccess
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getPokemonsUseCase: GetPokemonsUseCase,
) : BaseViewModel<MainViewModel.MainUiState>() {

    sealed class MainUiState {
        object InitialLoading : MainUiState()
        object PagingLoading : MainUiState()
        data class PokemonsLoaded(val data: List<Pokemon>) : MainUiState()
        data class FailedLoaded(val failure: Failure) : MainUiState()

    }

    fun getPokemons(page: Long) {

        viewModelScope.launch(dispatcherProvider.io) {
            getPokemonsUseCase.run(GetPokemonsUseCase.Params(page.toInt()))
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        it.onStart {
                            _uiState.value = if (page == 1L) {
                                MainUiState.InitialLoading
                            } else {
                                MainUiState.PagingLoading
                            }
                        }.collect { data ->
                            withContext(dispatcherProvider.main) {
                                _uiState.value = MainUiState.PokemonsLoaded(data)
                            }
                        }
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = MainUiState.FailedLoaded(it)
                    }
                }
        }
    }
}
