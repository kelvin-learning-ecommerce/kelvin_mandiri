package com.kelvin.mandiri.domain.usecase

import com.kelvin.mandiri.data.model.MovieDetailImageModel
import com.kelvin.mandiri.domain.repository.MovieRepository
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailImageUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<Int, Flow<Resource<MovieDetailImageModel>>>() {
    override fun execute(params: Int): Flow<Resource<MovieDetailImageModel>> {
        return movieRepository.getMovieDetailImage(params)
    }
}
