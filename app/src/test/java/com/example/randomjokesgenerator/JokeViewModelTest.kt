package com.example.randomjokesgenerator

import com.example.randomjokesgenerator.joke.data.Joke
import com.example.randomjokesgenerator.joke.data.JokeRepository
import com.example.randomjokesgenerator.joke.presentation.JokeUiState
import com.example.randomjokesgenerator.joke.presentation.JokeViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class JokeViewModelTest {

    private lateinit var viewModel: JokeViewModel
    private lateinit var repository: FakeJokeRepository

    @Before
    fun setup() {
        repository = FakeJokeRepository()
        viewModel = JokeViewModel(repository = repository)
    }

    /**
     * TestCase
     *
     * JokePage, Состояние Joke
     * Нажать на “next joke”
     * JokePage, Состояние Again
     *
     * */

    @Test
    fun test() {

        // first run
        var actualUiState = viewModel.init(true)
        var expectedUiState: JokeUiState = JokeUiState.Joke(
            category = "Category 0",
            joke = "Joke 0",
        )
        assertEquals(expectedUiState, actualUiState)
        assertEquals(true, repository.saveLastScreenIsCalled)

        // configuration changed
        actualUiState = viewModel.init(false)
        expectedUiState = JokeUiState.Empty
        assertEquals(expectedUiState, actualUiState)

        // next state
        actualUiState = viewModel.nextJoke()
        expectedUiState = JokeUiState.Joke(
            category = "Category 1",
            joke = "Joke 1",
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.nextJoke()
        expectedUiState = JokeUiState.NoMoreJokes
        assertEquals(expectedUiState, actualUiState)
    }
}

class FakeJokeRepository : JokeRepository {

    private val list = listOf(
        Joke(
            category = "Category 0",
            joke = "Joke 0",
        ),
        Joke(
            category = "Category 1",
            joke = "Joke 1",
        )
    )

    var saveLastScreenIsCalled = false
    private var currentJokeIndex = 0

    override fun nextJoke() {
        currentJokeIndex++
    }

    override fun isLast(): Boolean = currentJokeIndex == list.size

    override fun clear() {
        currentJokeIndex = 0
    }

    override fun getCurrentCategory(): String = list[currentJokeIndex].category
    override fun getCurrentJoke(): String = list[currentJokeIndex].joke

    override fun saveLastScreenIsJoke() {
        saveLastScreenIsCalled = true
    }
}