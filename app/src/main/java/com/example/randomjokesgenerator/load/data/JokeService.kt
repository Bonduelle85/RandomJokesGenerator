package com.example.randomjokesgenerator.load.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeService {

    // https://v2.jokeapi.dev/joke/Any?type=single&amount=10

    @GET("joke/Any")
    fun data(
        @Query("type") type: String = "single",
        @Query("amount") amount: Int = 10,
    ): Call<ResponseCloud>
}