package com.example.randomjokesgenerator.joke.presentation

import com.example.randomjokesgenerator.joke.views.button.UpdateButton
import com.example.randomjokesgenerator.joke.views.text.UpdateText
import java.io.Serializable

interface JokeUiState : Serializable {
    fun update(
        categoryView: UpdateText,
        jokeView: UpdateText,
        nextView: UpdateButton,
        downloadView: UpdateButton
    )

    fun navigate(showLoad: () -> Unit) = Unit

    data class Joke(private val category: String, private val joke: String) : JokeUiState {
        override fun update(
            categoryView: UpdateText,
            jokeView: UpdateText,
            nextView: UpdateButton,
            downloadView: UpdateButton
        ) {
            categoryView.updateText(category)
            jokeView.updateText(joke)
            nextView.changeEnabled(true)
            downloadView.changeEnabled(false)
        }
    }

    object NoMoreJokes : JokeUiState {
        override fun update(
            categoryView: UpdateText,
            jokeView: UpdateText,
            nextView: UpdateButton,
            downloadView: UpdateButton
        ) {
            nextView.changeEnabled(false)
            downloadView.changeEnabled(true)
        }

        override fun navigate(showLoad: () -> Unit) {
            showLoad.invoke()
        }

    }

    object Empty :JokeUiState {
        override fun update(
            categoryView: UpdateText,
            jokeView: UpdateText,
            nextView: UpdateButton,
            downloadView: UpdateButton
        ) = Unit
    }
}