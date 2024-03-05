package com.kelvin.mandiri.domain.repository

import com.kelvin.mandiri.data.model.Genres
import com.kelvin.mandiri.data.model.MovieDetailImageModel
import com.kelvin.mandiri.data.model.MovieDetailModel
import com.kelvin.mandiri.data.model.Results
import com.kelvin.mandiri.data.query.MovieListQuery
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(
        params: MovieListQuery
    ): Flow<Resource<List<Results>>>

    fun getGenre(): Flow<Resource<List<Genres>>>
    fun getMovieDetail(id: Int?): Flow<Resource<MovieDetailModel>>
    fun getMovieDetailImage(id: Int): Flow<Resource<MovieDetailImageModel>>

}
