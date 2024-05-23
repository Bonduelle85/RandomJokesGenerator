package com.example.randomjokesgenerator.main.di

import com.example.randomjokesgenerator.core.di.Core
import com.example.randomjokesgenerator.core.di.Module
import com.example.randomjokesgenerator.core.di.ProvideAbstract
import com.example.randomjokesgenerator.core.di.ProvideViewModel
import com.example.randomjokesgenerator.main.data.MainRepository
import com.example.randomjokesgenerator.main.presentation.MainViewModel

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel(): MainViewModel {
        return MainViewModel(MainRepository.Base(core.lastScreen))
    }
}

class ProvideMainViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, MainViewModel::class.java) {

    override fun module() = MainModule(core)
}