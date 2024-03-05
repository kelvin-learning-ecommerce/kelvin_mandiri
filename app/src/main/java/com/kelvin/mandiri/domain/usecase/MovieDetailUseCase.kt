package com.kelvin.mandiri.domain.usecase

import com.kelvin.mandiri.data.model.MovieDetailModel
import com.kelvin.mandiri.domain.repository.MovieRepository
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<Int, Flow<Resource<MovieDetailModel>>>() {
    override fun execute(params: Int): Flow<Resource<MovieDetailModel>> {
        return movieRepository.getMovieDetail(params)
    }
}
