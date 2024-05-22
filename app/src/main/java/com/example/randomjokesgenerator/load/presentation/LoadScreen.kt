package com.example.randomjokesgenerator.load.presentation

import androidx.fragment.app.Fragment
import com.example.randomjokesgenerator.main.presentation.Screen

object LoadScreen : Screen.Replace() {
    override fun fragment(): Fragment {
        return LoadFragment()
    }
}