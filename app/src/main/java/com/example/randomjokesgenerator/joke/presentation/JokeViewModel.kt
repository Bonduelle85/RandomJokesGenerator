package com.example.randomjokesgenerator.joke.presentation

import com.example.randomjokesgenerator.joke.data.JokeRepository
import com.example.randomjokesgenerator.main.presentation.MyViewModel

class JokeViewModel(
    private val repository: JokeRepository
) : MyViewModel {

    fun init(isFirstTime: Boolean): JokeUiState {
        return if (isFirstTime){
            repository.saveLastScreenIsJoke()
            val currentJoke = repository.getCurrentJoke()
            return JokeUiState.Joke(
                category = currentJoke.category,
                joke = currentJoke.joke,
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
            val currentJoke = repository.getCurrentJoke()
            JokeUiState.Joke(
                category = currentJoke.category,
                joke = currentJoke.joke
            )
        }

    }

    fun clear() {
        repository.clear()
    }

}