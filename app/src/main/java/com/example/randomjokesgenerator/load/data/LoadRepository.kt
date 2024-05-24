package com.example.randomjokesgenerator.load.data

import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.load.presentation.LoadScreen

interface LoadRepository {
    fun load(): LoadResult
    fun saveLastScreenIsLoad()

    class Base(
        private val lastScreen: StringCache,
        // CloudDataSource,
        //  CacheDataSource,
    ) : LoadRepository {
        override fun load(): LoadResult {
            TODO("Not yet implemented")
        }


        override fun saveLastScreenIsLoad() {
            lastScreen.save(LoadScreen::class.java.canonicalName!!)
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
