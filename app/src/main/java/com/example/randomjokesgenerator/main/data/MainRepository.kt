package com.example.randomjokesgenerator.main.data

import com.example.randomjokesgenerator.core.data.StringCache
import com.example.randomjokesgenerator.main.presentation.Screen

interface MainRepository {

    fun lastSavedScreen(): Screen

    class Base(
        private val lastScreen: StringCache,
    ) : MainRepository{
        override fun lastSavedScreen(): Screen {
            val string = lastScreen.read()
            return Class.forName(string).getDeclaredConstructor().newInstance() as Screen
        }
    }
}