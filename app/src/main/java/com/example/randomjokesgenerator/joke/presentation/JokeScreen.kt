package com.example.randomjokesgenerator.joke.presentation

import androidx.fragment.app.Fragment
import com.example.randomjokesgenerator.main.presentation.Screen

object JokeScreen : Screen.Replace() {
    override fun fragment(): Fragment {
        return JokeFragment()
    }
}