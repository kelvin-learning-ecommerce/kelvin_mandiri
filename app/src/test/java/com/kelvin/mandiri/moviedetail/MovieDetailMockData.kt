package com.kelvin.mandiri.moviedetail

import com.kelvin.mandiri.data.model.MovieDetailModel
import com.kelvin.mandiri.data.model.Posters

class MovieDetailMockData {
    companion object {
        val movieDetailData = MovieDetailModel()
        val movieImageDetailData = Posters()

        //list of 4 coins
        val listOfImageData = listOf(
            movieImageDetailData, movieImageDetailData, movieImageDetailData, movieImageDetailData
        )
    }
}
