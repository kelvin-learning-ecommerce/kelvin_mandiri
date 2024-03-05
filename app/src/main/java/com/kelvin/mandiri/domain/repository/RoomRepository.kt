package com.kelvin.mandiri.domain.repository

import com.kelvin.mandiri.data.room.model.MovieDaoModel

interface RoomRepository {
    fun getAll(): List<MovieDaoModel>
    fun loadAllByIds(userIds: IntArray): List<MovieDaoModel>
    fun insertMovie(vararg movie: MovieDaoModel)
    fun delete(user: MovieDaoModel)
    fun deleteByMovieId(id: Int)


}
