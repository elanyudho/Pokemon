package com.elanyudho.pokemon.ui.detail

import androidx.lifecycle.viewModelScope
import com.elanyudho.core.abstraction.BaseViewModel
import com.elanyudho.core.dispatcher.DispatcherProvider
import com.elanyudho.core.domain.model.DetailPokemon
import com.elanyudho.core.domain.usecase.GetDetailPokemonUseCase
import com.elanyudho.core.util.exception.Failure
import com.elanyudho.core.util.extension.onError
import com.elanyudho.core.util.extension.onSuccess
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailPokemonViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getDetailPokemonUseCase: GetDetailPokemonUseCase,
) : BaseViewModel<DetailPokemonViewModel.DetailPokemonUiState>() {

    sealed class DetailPokemonUiState {
        object Loading : DetailPokemonUiState()
        data class DetailPokemonLoaded(val data: DetailPokemon) : DetailPokemonUiState()
        data class FailedLoaded(val failure: Failure) : DetailPokemonUiState()
    }

    fun getDetailPokemon(id: String) {
        _uiState.value = DetailPokemonUiState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            getDetailPokemonUseCase.run(id)
                .onSuccess {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = DetailPokemonUiState.DetailPokemonLoaded(it)
                    }
                }
                .onError {
                    withContext(dispatcherProvider.main) {
                        _uiState.value = DetailPokemonUiState.FailedLoaded(it)
                    }
                }
        }
    }
}
