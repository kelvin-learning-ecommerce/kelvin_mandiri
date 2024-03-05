package com.kelvin.mandiri.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kelvin.mandiri.data.room.model.MovieDaoModel

@Database(entities = [MovieDaoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
