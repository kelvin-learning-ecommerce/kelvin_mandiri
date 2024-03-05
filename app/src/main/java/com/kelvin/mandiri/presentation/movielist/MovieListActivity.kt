package com.kelvin.mandiri.ui.movielist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kelvin.mandiri.data.model.MovieUIModel
import com.kelvin.mandiri.presentation.movielist.components.Content
import com.kelvin.mandiri.presentation.movielist.components.MovieListScreen
import com.kelvin.mandiri.presentation.utils.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieListScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Content(
                data = listOf(
                    MovieUIModel(
                        title = "Test title",
                        posterPath = "/7lTnXOy0iNtBAdRP3TZvaKJ77F6.jpg",
                        voteAverage = "7",
                        releaseDate = "20-11-2010",
                        voteCount = 100
                    )
                ),
                onItemFav = {
                 }
            )
        }
    }
}
