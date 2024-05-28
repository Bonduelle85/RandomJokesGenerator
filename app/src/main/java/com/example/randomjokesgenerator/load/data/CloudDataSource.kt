package com.example.randomjokesgenerator.load.data

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import java.net.UnknownHostException

interface CloudDataSource {

    suspend fun data(): List<CategoryAndJokeCloud>

    class Base(private val jokeService: JokeService) : CloudDataSource {

        override suspend fun data(): List<CategoryAndJokeCloud> {
            try {
                val data: Call<ResponseCloud> = jokeService.data()
                return data.execute().body()!!.items
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    throw IllegalStateException("No internet connection")
                else
                    throw IllegalStateException("unknown error")
            }
        }
    }
}

data class ResponseCloud(
    @SerializedName("jokes")
    val items: List<CategoryAndJokeCloud>
)

data class CategoryAndJokeCloud(
    @SerializedName("category")
    val category: String,
    @SerializedName("joke")
    val joke: String,
)