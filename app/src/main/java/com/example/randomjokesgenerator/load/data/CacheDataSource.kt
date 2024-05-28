package com.example.randomjokesgenerator.load.data

import com.example.randomjokesgenerator.core.data.StringCache
import com.google.gson.Gson
import java.io.Serializable

interface CacheDataSource {

    suspend fun save(data: ResponseCloud)

    fun read(): List<CategoryAndJokeCloud>

    class Base(
        private val stringCache: StringCache,
        private val gson: Gson
    ) : CacheDataSource {

        override suspend fun save(data: ResponseCloud) {
            stringCache.save(gson.toJson(data))
        }

        override fun read(): List<CategoryAndJokeCloud> {
            return gson.fromJson(stringCache.read(), ResponseCloud::class.java).items
        }
    }
}