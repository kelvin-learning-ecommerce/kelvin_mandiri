package com.kelvin.mandiri.movielist

import com.kelvin.mandiri.data.model.MovieUIModel

class MovieMockData {
    companion object {
        val movie = MovieUIModel(
            id = 572802,
            overview = "en",
            title = "Aquaman and the Lost Kingdom"
        )

        //list of 4 coins
        val listOfMovie = listOf(
            movie, movie, movie, movie
        )
    }
}
