package com.kelvin.mandiri.core.network.api

import com.kelvin.mandiri.data.model.GenreModel
import com.kelvin.mandiri.data.model.Genres
import com.kelvin.mandiri.data.model.MovieDetailImageModel
import com.kelvin.mandiri.data.model.MovieDetailModel
import com.kelvin.mandiri.data.model.MovieListModel
import com.kelvin.mandiri.data.model.Results
import com.kelvin.mandiri.presentation.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException
import javax.inject.Inject

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getGenre(
    ): Response<GenreModel>

    @GET("discover/movie")
    suspend fun getMovieList(@Query("page") page: Int? = 1, @Query("with_genres") genreId: Int? = 28): Response<MovieListModel>

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): Response<MovieDetailModel>

    @GET("movie/{movie_id}/images")
    suspend fun getMovieDetailImage(@Path("movie_id") id: Int): Response<MovieDetailImageModel>
}

class MovieService @Inject constructor(private val api: MovieApi) {
    fun getMovieList(page: Int? = 1, genreId: Int? = 28): Flow<Resource<List<Results>>> = flow {
        emit(Resource.Loading())
        try {
            val genre = api.getMovieList(page, genreId)

            emit(Resource.Success(genre.body()?.results ?: emptyList()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }

    fun getGenre(): Flow<Resource<List<Genres>>> = flow {
        emit(Resource.Loading())
        try {
            val genre = api.getGenre()

            emit(Resource.Success(genre.body()?.genres ?: emptyList()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }

    fun getMovieDetail(id: Int): Flow<Resource<MovieDetailModel>> = flow {
        emit(Resource.Loading())
        try {
            val genre = api.getMovieDetail(id)

            emit(Resource.Success(genre.body()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }

    fun getMovieDetailImage(id: Int): Flow<Resource<MovieDetailImageModel>> = flow {
        emit(Resource.Loading())
        try {
            val genre = api.getMovieDetailImage(id)

            emit(Resource.Success(genre.body()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
    }
}
