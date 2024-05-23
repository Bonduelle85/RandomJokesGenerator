package com.example.randomjokesgenerator.joke.di

import com.example.randomjokesgenerator.core.di.Core
import com.example.randomjokesgenerator.core.di.Module
import com.example.randomjokesgenerator.core.di.ProvideAbstract
import com.example.randomjokesgenerator.core.di.ProvideViewModel
import com.example.randomjokesgenerator.joke.data.JokeRepository
import com.example.randomjokesgenerator.joke.presentation.JokeViewModel

class JokeModule(private val core: Core) : Module<JokeViewModel> {

    override fun viewModel(): JokeViewModel = with(core) {
        return JokeViewModel(
            JokeRepository.Base(

            )
        )
    }
}

class ProvideJokeViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, JokeViewModel::class.java) {

    override fun module() = JokeModule(core)
}