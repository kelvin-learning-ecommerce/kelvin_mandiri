package com.kelvin.mandiri.domain.usecase

import com.kelvin.mandiri.data.model.Results
import com.kelvin.mandiri.data.query.MovieListQuery
import com.kelvin.mandiri.domain.repository.MovieRepository
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<MovieListQuery, Flow<Resource<List<Results>>>>() {
    override fun execute(params: MovieListQuery): Flow<Resource<List<Results>>> {
        return movieRepository.getMovieList(params)
    }
}
