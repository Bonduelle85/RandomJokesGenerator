package com.example.randomjokesgenerator.joke.presentation

import java.io.Serializable

interface JokeUiState : Serializable {


    data class Joke(private val category: String, private val joke: String) : JokeUiState {

    }

    object Again : JokeUiState {

    }
}