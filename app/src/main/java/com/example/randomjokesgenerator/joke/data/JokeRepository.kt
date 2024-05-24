package com.example.randomjokesgenerator.joke.data

import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.joke.presentation.JokeScreen

interface JokeRepository {

    fun getCurrentCategory() : String
    fun getCurrentJoke() : String
    fun nextJoke()
    fun isLast(): Boolean
    fun clear()
    fun saveLastScreenIsJoke()

    class Base(
        private val cachedJokes: CacheDataSource,
        private val lastScreen: StringCache,
        private val max: Int
    ) : JokeRepository {

        private var currentIndex = 0

        override fun getCurrentCategory(): String {
            val list: List<Joke> = cachedJokes.read()
            return list[currentIndex].category
        }

        override fun getCurrentJoke(): String {
            val list: List<Joke> = cachedJokes.read()
            return list[currentIndex].joke
        }

        override fun nextJoke() {
            currentIndex++
        }

        override fun isLast(): Boolean = currentIndex == max

        override fun clear() {
            currentIndex = 0
        }

        override fun saveLastScreenIsJoke() {
            lastScreen.save(JokeScreen::class.java.canonicalName!!)
        }
    }
}

data class Joke(
    val category: String,
    val joke: String,
)