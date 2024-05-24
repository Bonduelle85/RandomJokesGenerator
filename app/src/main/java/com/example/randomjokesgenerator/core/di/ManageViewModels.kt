package com.example.randomjokesgenerator.core.di

import com.example.randomjokesgenerator.joke.di.ProvideJokeViewModel
//import com.example.randomjokesgenerator.load.di.ProvideLoadViewModel
import com.example.randomjokesgenerator.main.di.ProvideMainViewModel
import com.example.randomjokesgenerator.main.presentation.MyViewModel

interface ManageViewModels : ClearViewModel, ProvideViewModel

interface ClearViewModel {
    fun clear(clazz: Class<out MyViewModel>)
}

interface ProvideViewModel {
    fun <T : MyViewModel> viewModel(clazz: Class<T>): T


    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {

        private val mutableMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun clear(clazz: Class<out MyViewModel>) {
            mutableMap[clazz] = null
        }

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return if (mutableMap[clazz] == null) {
                val viewModel = make.viewModel(clazz)
                mutableMap[clazz] = viewModel
                viewModel
            } else
                mutableMap[clazz] as T
        }
    }

    class Make(core: Core) : ProvideViewModel {

//        private val chain: ProvideViewModel

        init {
            var temp: ProvideViewModel = Error()
            temp = ProvideMainViewModel(core, temp)
            temp = ProvideJokeViewModel(core, temp)
//            chain = ProvideLoadViewModel(core, temp)
        }

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return TODO() // chain.viewModel(clazz)
        }
    }
}