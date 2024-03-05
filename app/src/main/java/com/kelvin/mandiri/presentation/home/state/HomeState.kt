package com.kelvin.mandiri.presentation.home.state

import com.kelvin.mandiri.data.model.Genres

data class HomeState(
    val genreList: List<Genres> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
