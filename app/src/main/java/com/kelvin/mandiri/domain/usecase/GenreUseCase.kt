package com.kelvin.mandiri.domain.usecase

import com.kelvin.mandiri.data.model.Genres
import com.kelvin.mandiri.domain.repository.MovieRepository
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, Flow<Resource<List<Genres>>>>() {
    override fun execute(params: Unit): Flow<Resource<List<Genres>>> {
        return movieRepository.getGenre()
    }
}
