package com.example.randomjokesgenerator.core.di

import com.example.randomjokesgenerator.main.presentation.MyViewModel

interface Module<T : MyViewModel> {

    fun viewModel(): T
}