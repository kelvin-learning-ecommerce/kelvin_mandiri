package com.kelvin.mandiri.presentation.moviedetail.state

import com.kelvin.mandiri.data.model.MovieDetailModel
import com.kelvin.mandiri.data.model.Posters

data class MovieDetailState(
    val isLoading: Boolean = false,
    val data: MovieDetailModel? = null,
    val imageData: List<Posters>? = emptyList(),
    val error: String = ""
    )
