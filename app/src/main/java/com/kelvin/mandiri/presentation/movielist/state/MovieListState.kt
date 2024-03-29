package com.kelvin.mandiri.presentation.movielist.state

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kelvin.mandiri.data.model.MovieUIModel
import com.kelvin.mandiri.presentation.common.ShimmerListItem
import com.kelvin.mandiri.presentation.movielist.viewmodel.MovieListViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MovieListState(
    val isLoading: Boolean = false,
    val data: ImmutableList<MovieUIModel> = persistentListOf(),
    val error: String = ""
    )

@Composable
fun BoxScope.MovieListScreenState(
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    when {
        state.isLoading -> {

            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(10) {
                    ShimmerListItem()
                }
            }
        }

        state.error.isNotEmpty() -> {

            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
    }

}
