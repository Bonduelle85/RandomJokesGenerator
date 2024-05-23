package com.example.randomjokesgenerator.joke.presentation

import com.example.randomjokesgenerator.joke.data.JokeRepository
import com.example.randomjokesgenerator.main.presentation.MyViewModel

class JokeViewModel(
    private val repository: JokeRepository
) : MyViewModel {
    fun init(): JokeUiState {
        return JokeUiState.Joke(
            category = repository,
            joke = repository
        )
    }

    fun nextJoke(): JokeUiState {
        repository.nextJoke()
    }

}