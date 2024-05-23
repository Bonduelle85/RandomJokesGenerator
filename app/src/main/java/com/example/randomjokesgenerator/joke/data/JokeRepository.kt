package com.example.randomjokesgenerator.joke.data

interface JokeRepository {

    fun getCurrentJoke(): Pair<String, String>
    fun nextJoke()
    fun isLast(): Boolean

    class Base(
        cachedJokes: CacheDataSource,
    ) : JokeRepository {

        private val list: List<Pair<String, String>> = cachedJokes.read()

        override fun getCurrentJoke(): Pair<String, String> {
            TODO("Not yet implemented")
        }

        override fun nextJoke() {

        }

        override fun isLast(): Boolean {
            TODO("Not yet implemented")
        }
    }
}