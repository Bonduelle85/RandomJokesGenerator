package com.example.randomjokesgenerator.load.data

import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.load.presentation.LoadScreen

interface LoadRepository {
    fun load(): LoadResult
    fun saveLastScreenIsLoad()

    class Base(
        private val lastScreen: StringCache,
        private val cloudDataSource: CloudDataSource,
        private val cacheDataSource: CacheDataSource,
    ) : LoadRepository {
        override fun load(): LoadResult {
            return try {
                val data: List<CategoryAndJokeCloud> = cloudDataSource.data()
                cacheDataSource.save(ResponseCloud(items = data))
                LoadResult.Success
            } catch (e: Exception) {
                LoadResult.Error(e.message ?: "error")
            }
        }

        override fun saveLastScreenIsLoad() {
            LoadScreen::class.java.canonicalName?.let { lastScreen.save(it) }
            // lastScreen.save(LoadScreen::class.java.canonicalName!!)
        }
    }
}



interface LoadResult {

    fun isSuccessful(): Boolean

    fun message(): String

    object Success : LoadResult {

        override fun isSuccessful(): Boolean {
            return true
        }

        override fun message(): String {
            throw IllegalStateException()
        }
    }

    data class Error(private val message: String) : LoadResult {

        override fun isSuccessful(): Boolean {
            return false
        }

        override fun message(): String {
            return message
        }
    }
}
