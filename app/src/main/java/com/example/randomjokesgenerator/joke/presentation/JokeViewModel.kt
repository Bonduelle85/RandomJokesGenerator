package com.example.randomjokesgenerator.joke.presentation

import com.example.randomjokesgenerator.joke.data.JokeRepository
import com.example.randomjokesgenerator.main.presentation.MyViewModel

class JokeViewModel(
    private val repository: JokeRepository
) : MyViewModel {

    fun init(isFirstTime: Boolean): JokeUiState {
        return if (isFirstTime){
            repository.saveLastScreenIsJoke()
            return JokeUiState.Joke(
                category = repository.getCurrentCategory(),
                joke = repository.getCurrentJoke(),
            )
        } else {
            JokeUiState.Empty
        }
    }

    fun nextJoke(): JokeUiState {
        repository.nextJoke()
        return if (repository.isLast()) {
            JokeUiState.NoMoreJokes
        } else {
            JokeUiState.Joke(
                category = repository.getCurrentCategory(),
                joke = repository.getCurrentJoke()
            )
        }

    }

    fun clear() {
        repository.clear()
    }

}