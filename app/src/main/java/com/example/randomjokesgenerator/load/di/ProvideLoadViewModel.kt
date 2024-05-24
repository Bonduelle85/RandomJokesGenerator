//package com.example.randomjokesgenerator.load.di
//
//import com.example.randomjokesgenerator.core.di.Core
//import com.example.randomjokesgenerator.core.di.Module
//import com.example.randomjokesgenerator.core.di.ProvideAbstract
//import com.example.randomjokesgenerator.core.di.ProvideViewModel
//import com.example.randomjokesgenerator.load.presentation.LoadViewModel
//
//class LoadModule(
//    private val core: Core
//) : Module<LoadViewModel> {
//
//    override fun viewModel(): LoadViewModel = with(core) {
//        return LoadViewModel(
//
//        )
//    }
//}
//
//class ProvideLoadViewModel(
//    core: Core,
//    provideOther: ProvideViewModel
//) : ProvideAbstract(core, provideOther, LoadViewModel::class.java) {
//
//    override fun module() = LoadModule(core)
//}