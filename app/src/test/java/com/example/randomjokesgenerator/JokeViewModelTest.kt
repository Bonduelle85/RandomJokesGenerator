package com.example.randomjokesgenerator

import com.example.randomjokesgenerator.joke.data.JokeRepository
import com.example.randomjokesgenerator.joke.presentation.JokeUiState
import com.example.randomjokesgenerator.joke.presentation.JokeViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class JokeViewModelTest {

    private lateinit var viewModel: JokeViewModel
    private lateinit var actualUiState: JokeUiState
    private lateinit var expectedUiState: JokeUiState

    @Before
    fun setup() {
        viewModel = JokeViewModel(repository = FakeJokeRepository())
    }

    @Test
    fun caseTest() {
        actualUiState = viewModel.init()
        expectedUiState = JokeUiState.Joke(
            category = "Category 0",
            joke = "Joke 0",
        )
        assertEquals(expectedUiState, actualUiState)

        repeat(8) {
            actualUiState = viewModel.nextJoke()
            expectedUiState = JokeUiState.Joke(
                category = "Category ${it+1}",
                joke = "Joke ${it+1}",
            )
            assertEquals(expectedUiState, actualUiState)
        }

        actualUiState = viewModel.nextJoke()
        expectedUiState = JokeUiState.Again
        assertEquals(expectedUiState, actualUiState)
    }
}

private class FakeJokeRepository: JokeRepository {

    private val list = listOf<Pair<String, String>>(
        "Category 0" to "Joke 0",
        "Category 1" to "Joke 1",
        "Category 2" to "Joke 2",
        "Category 3" to "Joke 3",
        "Category 4" to "Joke 4",
        "Category 5" to "Joke 5",
        "Category 6" to "Joke 6",
        "Category 7" to "Joke 7",
        "Category 8" to "Joke 8",
        "Category 9" to "Joke 9",
    )

    private var currentJokeIndex = 0

    override fun getCurrentJoke(): Pair<String, String> = list[currentJokeIndex]

    override fun nextJoke() {
        currentJokeIndex++
    }

    override fun isLast(): Boolean = list[9] == "Category 9" to "Joke 9"

    fun reset() {
        currentJokeIndex = 0
    }
}