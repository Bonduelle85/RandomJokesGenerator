package com.example.randomjokesgenerator.load.di

import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.core.di.Core
import com.example.randomjokesgenerator.core.di.Core.Companion.CACHED_JOKES
import com.example.randomjokesgenerator.core.di.Module
import com.example.randomjokesgenerator.core.di.ProvideAbstract
import com.example.randomjokesgenerator.core.di.ProvideViewModel
import com.example.randomjokesgenerator.load.data.CacheDataSource
import com.example.randomjokesgenerator.load.data.CloudDataSource
import com.example.randomjokesgenerator.load.data.FakeService
import com.example.randomjokesgenerator.load.data.JokeService
import com.example.randomjokesgenerator.load.data.LoadRepository
import com.example.randomjokesgenerator.load.data.ResponseCloud
import com.example.randomjokesgenerator.load.presentation.LoadViewModel
import com.example.randomjokesgenerator.load.presentation.UiObservable
import com.example.randomjokesgenerator.main.presentation.RunAsync
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoadModule(
    private val core: Core
) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        return LoadViewModel(
            UiObservable.Base(),
            LoadRepository.Base(
                lastScreen = core.lastScreen,
                cloudDataSource = CloudDataSource.Base(
                    if (core.runUiTest)
                        FakeService()
                    else
                        getJokeService()
                ),
                cacheDataSource = CacheDataSource.Base(
                    StringCache.Base(
                        CACHED_JOKES,
                        core.sharedPreferences,
                        core.gson.toJson(ResponseCloud(emptyList()))
                    ),
                    core.gson
                ),

                ),
            RunAsync.Base()
        )
    }

    private fun getJokeService(): JokeService {
        return Retrofit.Builder()
            //  https://v2.jokeapi.dev/joke/Any?type=single&amount=10
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                    .build()
            )
            .build()
            .create(JokeService::class.java)
    }
}

class ProvideLoadViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, LoadViewModel::class.java) {

    override fun module() = LoadModule(core)
}