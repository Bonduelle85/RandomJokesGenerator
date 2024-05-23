package com.example.randomjokesgenerator.core.di

import android.content.Context
import com.example.randomjokesgenerator.R
import com.example.randomjokesgenerator.core.data.IntCache
import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.load.presentation.LoadScreen

class Core(context: Context) {

    val runUiTest: Boolean = false

    val sharedPreferencesFileName =
        if (runUiTest) "ui_test"
        else context.getString(R.string.app_name)

    val sharedPreferences =
        context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

    val currentJokeIndex = IntCache.Base(CURRENT_JOKE_INDEX, sharedPreferences)
    val lastScreen =
        StringCache.Base(LAST_SCREEN, sharedPreferences, LoadScreen::class.java.canonicalName!!)


    companion object {
        const val CURRENT_JOKE_INDEX = "CURRENT_INDEX"
        const val LAST_SCREEN = "LAST_SCREEN"
    }
}