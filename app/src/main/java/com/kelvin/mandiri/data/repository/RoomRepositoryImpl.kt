package com.kelvin.mandiri.data.repository

import com.kelvin.mandiri.data.room.AppDatabase
import com.kelvin.mandiri.data.room.model.MovieDaoModel
import com.kelvin.mandiri.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase) :
    RoomRepository {
    override fun getAll(): List<MovieDaoModel> {
        return appDatabase.movieDao().getAll()
    }

    override fun loadAllByIds(userIds: IntArray): List<MovieDaoModel> {
        return appDatabase.movieDao().loadAllByIds(userIds)

    }

    override fun insertMovie(vararg movie: MovieDaoModel) {
        return appDatabase.movieDao().insertMovie(movie = movie)

    }

    override fun delete(user: MovieDaoModel) {
        return appDatabase.movieDao().delete(user)

    }

    override fun deleteByMovieId(id: Int) {
        return appDatabase.movieDao().deleteByMovieId(id)
    }
}
