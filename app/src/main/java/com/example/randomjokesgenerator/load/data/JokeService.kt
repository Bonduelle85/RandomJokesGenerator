package com.example.randomjokesgenerator.load.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.Request
import okio.Timeout
import retrofit2.Callback
import retrofit2.Response

import java.net.UnknownHostException

interface JokeService {

    // https://v2.jokeapi.dev/joke/Any?type=single&amount=10

    @GET("joke/Any")
    fun data(
        @Query("type") type: String = "single",
        @Query("amount") amount: Int = 10,
    ): Call<ResponseCloud>

}

    class FakeService : JokeService {

        private val response = ResponseCloud(
            List(10) {
                CategoryAndJokeCloud(
                    "Category$it",
                    "Joke$it",
                )
            }
        )

        private var showSuccess = false

        override fun data(type: String, amount: Int): Call<ResponseCloud> {
            Thread.sleep(1000)
            if (showSuccess)
                return object : Call<ResponseCloud> {
                    override fun clone(): Call<ResponseCloud> {
                        return this
                    }

                    override fun execute(): Response<ResponseCloud> {
                        return Response.success(response)
                    }

                    override fun isExecuted(): Boolean {
                        return false
                    }

                    override fun cancel() {
                    }

                    override fun isCanceled(): Boolean {
                        return false
                    }

                    override fun request(): Request {
                        TODO("Not yet implemented")
                    }

                    override fun timeout(): Timeout {
                        TODO("Not yet implemented")
                    }

                    override fun enqueue(callback: Callback<ResponseCloud>) {
                        TODO("Not yet implemented")
                    }

                }
            else {
                showSuccess = true
                throw UnknownHostException()
            }
        }
}