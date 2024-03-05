package com.kelvin.mandiri.genre

import com.google.common.truth.Truth.assertThat
import com.kelvin.mandiri.dispatcher.MainDispatcherRule
import com.kelvin.mandiri.domain.usecase.GenreUseCase
import com.kelvin.mandiri.genre.MockData.Companion.genres
import com.kelvin.mandiri.genre.MockData.Companion.listOfGenre
import com.kelvin.mandiri.presentation.common.Resource
import com.kelvin.mandiri.presentation.home.viewmodel.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GenreViewModelTests {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var genreUseCase: GenreUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        genreUseCase = mockk(relaxed = true)
        viewModel = HomeViewModel(genreUseCase)
    }

    @Test
    fun `onRequestLoading when genre state is empty update isLoading state to true`() = runTest {
        viewModel.updateState(genres = emptyList())
        viewModel.onRequestLoading()

        val isLoadingState = viewModel.homeState.value.isLoading

        assertThat(isLoadingState).isTrue()
    }

    @Test
    fun `onRequestLoading when genre state is not empty update isLoading pagination state to true`() =
        runTest {
            viewModel.updateState(genres = listOfGenre)
            viewModel.onRequestLoading()

            val isLoadingState = viewModel.homeState.value.isLoading

            assertThat(isLoadingState).isFalse()
        }

    @Test
    fun `when error message is not null update error state`() = runTest {
        val errorMsg = "Hi This is an Error"

        viewModel.onRequestError(errorMsg)

        val errorState = viewModel.homeState.value.error

        assertThat(errorState).isNotEmpty()
    }

    @Test
    fun `onRequestSuccess update the genre list state`() = runTest {
        val testList = listOf(genres, genres)
        viewModel.updateState(genres = testList)

        viewModel.onRequestSuccess(listOfGenre)

        val genreState = viewModel.homeState.value.genreList + testList
        val expectedList = testList + listOfGenre

        assertThat(genreState).isNotEmpty()
        assertThat(genreState).isEqualTo(expectedList)
    }

    @Test
    fun `when getgenre called and result is Success`() = runTest {
//        val skip = viewModel.paginationState.value.skip
        val data = List(10) { index -> genres.copy() }

        every { genreUseCase.execute(Unit) } returns flowOf(Resource.Success(data))
        viewModel.getGenreList()

        val actualgenreList = viewModel.homeState.value.genreList

        assertThat(actualgenreList).isEqualTo(data)
    }

    @Test
    fun `when getgenre called and result is Error`() = runTest {
        val errorMsg = "Error occurred"
        every { genreUseCase.execute(Unit) } returns flowOf(Resource.Error(errorMsg))
        viewModel.getGenreList()

        val actualError = viewModel.homeState.value.error

        assertThat(actualError).isNotEmpty()
        assertThat(actualError).isEqualTo(errorMsg)
    }

    @Test
    fun `when getgenre called and result is Loading when genre state is empty`() = runTest {
        viewModel.updateState()

        every { genreUseCase.execute(Unit) } returns flowOf(Resource.Loading())
        viewModel.getGenreList()

        val actualIsLoading = viewModel.homeState.value.isLoading

        assertThat(actualIsLoading).isTrue()
    }

    @Test
    fun `when getgenre called and result is Loading when genre state is not empty`() = runTest {
        viewModel.updateState(genres = listOfGenre)

        every { genreUseCase.execute(Unit) } returns flowOf(Resource.Loading())
        viewModel.getGenreList()

        val actualIsLoading = viewModel.homeState.value.isLoading

        assertThat(actualIsLoading).isFalse()
    }
}
