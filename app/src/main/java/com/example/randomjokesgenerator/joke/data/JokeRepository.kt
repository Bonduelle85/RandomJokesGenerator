package com.example.randomjokesgenerator.joke.data

import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.joke.presentation.JokeScreen
import com.example.randomjokesgenerator.load.data.CacheDataSource
import com.example.randomjokesgenerator.load.data.CategoryAndJokeCloud

interface JokeRepository {

    fun getCurrentJoke(): Joke
    fun nextJoke()
    fun isLast(): Boolean
    fun clear()
    fun saveLastScreenIsJoke()

    class Base(
        private val lastScreen: StringCache,
        private val cachedJokes: CacheDataSource,
        private val max: Int
    ) : JokeRepository {

        private var currentIndex = 0

        override fun getCurrentJoke(): Joke {
            val list: List<CategoryAndJokeCloud> = cachedJokes.read()
            return Joke(
                category = list[currentIndex].category,
                joke = list[currentIndex].joke
            )
        }

        override fun nextJoke() {
            currentIndex++
        }

        override fun isLast(): Boolean = currentIndex == max

        override fun clear() {
            currentIndex = 0
        }

        override fun saveLastScreenIsJoke() {
            JokeScreen::class.java.canonicalName?.let { lastScreen.save(it) }
            // lastScreen.save(JokeScreen::class.java.canonicalName!!)
        }
    }
}

data class Joke(
    val category: String,
    val joke: String,
)