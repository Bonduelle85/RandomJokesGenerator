package com.example.randomjokesgenerator.core.di

import android.content.Context
import com.example.randomjokesgenerator.R
import com.example.randomjokesgenerator.core.data.IntCache
import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.load.data.CacheDataSource
import com.example.randomjokesgenerator.load.presentation.LoadScreen
import com.google.gson.Gson

class Core(context: Context) {

    val max = 10
    val runUiTest: Boolean = false

    val gson = Gson()
    val sharedPreferencesFileName =
        if (runUiTest) "ui_test"
        else context.getString(R.string.app_name)
    val sharedPreferences =
        context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)
    val cachedJokes = CacheDataSource.Base(
        StringCache.Base(CACHED_JOKES, sharedPreferences, ""),
        Gson()
    )
    val lastScreen =
        StringCache.Base(LAST_SCREEN, sharedPreferences, LoadScreen::class.java.canonicalName!!)


    companion object {
        const val LAST_SCREEN = "LAST_SCREEN"
        const val CACHED_JOKES = "CACHED_JOKES"
    }
}