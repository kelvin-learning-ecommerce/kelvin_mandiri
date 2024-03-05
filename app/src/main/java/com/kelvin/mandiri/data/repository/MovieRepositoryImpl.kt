package com.kelvin.mandiri.data.repository

import com.kelvin.mandiri.core.network.api.MovieService
import com.kelvin.mandiri.data.model.Genres
import com.kelvin.mandiri.data.model.MovieDetailImageModel
import com.kelvin.mandiri.data.model.MovieDetailModel
import com.kelvin.mandiri.data.model.Results
import com.kelvin.mandiri.data.query.MovieListQuery
import com.kelvin.mandiri.domain.repository.MovieRepository
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override fun getMovieList(params: MovieListQuery): Flow<Resource<List<Results>>> {
        return movieService.getMovieList(params.page, params.genreId)
    }

    override fun getGenre(): Flow<Resource<List<Genres>>> {
        return movieService.getGenre()
    }

    override fun getMovieDetail(id: Int?): Flow<Resource<MovieDetailModel>> {
        return movieService.getMovieDetail(id ?: 0)
    }

    override fun getMovieDetailImage(id: Int): Flow<Resource<MovieDetailImageModel>> {
        return movieService.getMovieDetailImage(id)
    }
}
