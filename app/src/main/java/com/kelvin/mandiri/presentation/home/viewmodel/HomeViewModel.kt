package com.kelvin.mandiri.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvin.mandiri.data.model.Genres
import com.kelvin.mandiri.domain.usecase.GenreUseCase
import com.kelvin.mandiri.presentation.common.Resource
import com.kelvin.mandiri.presentation.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreUseCase: GenreUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> get() = _state

    init {
        getGenreList()
    }

    fun getGenreList() {
        viewModelScope.launch {
            genreUseCase.execute(Unit)
                .distinctUntilChanged()
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> result.data?.let { data -> onRequestSuccess(data) }
                        is Resource.Error -> onRequestError(result.message)
                        is Resource.Loading -> onRequestLoading()
                    }
                }
        }
    }

    internal fun onRequestSuccess(
        data: List<Genres>
    ) {
        _state.value = _state.value.copy(
            genreList = data,
            isLoading = false
        )
    }

    internal fun onRequestLoading() {
        if (_state.value.genreList.isEmpty()) {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
        }
    }

    internal fun onRequestError(
        message: String?
    ) {
        _state.update {
            it.copy(
                error = message ?: "Unexpected Error",
                isLoading = false,
            )
        }
    }

    fun updateState(
        isLoading: Boolean = false,
        genres: List<Genres> = emptyList(),
        error: String = ""
    ) {
        _state.update {
            it.copy(
                isLoading = isLoading,
                genreList = genres.toImmutableList(),
                error = error
            )
        }
    }
}
