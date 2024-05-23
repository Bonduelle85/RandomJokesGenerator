package com.example.randomjokesgenerator.core.di

import com.example.randomjokesgenerator.main.presentation.MyViewModel

class Error : ProvideViewModel {

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        throw IllegalStateException("unknown viewModel $clazz go and add it to ProvideViewModel.Make")
    }
}