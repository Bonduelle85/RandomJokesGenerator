package com.example.randomjokesgenerator.load.presentation

import com.example.randomjokesgenerator.load.data.LoadRepository
import com.example.randomjokesgenerator.main.presentation.MyViewModel
import com.example.randomjokesgenerator.main.presentation.RunAsync

class LoadViewModel(
    private val uiObservable: UiObservable,
    private val repository: LoadRepository,
    runAsync: RunAsync,
) : MyViewModel.Abstract(runAsync) {

    fun init(firstRun: Boolean) {
        if (firstRun) {
            repository.saveLastScreenIsLoad()
            uiObservable.updateUiState(LoadUiState.Progress)
            runAsync(repository::load) { loadResult ->
                if (loadResult.isSuccessful())
                    uiObservable.updateUiState(LoadUiState.Success)
                else
                    uiObservable.updateUiState(LoadUiState.Error(loadResult.message()))
            }
        }
    }

    fun retry() = init(true)

    fun startGettingUpdates(showUi: (LoadUiState) -> Unit) {
        uiObservable.updateObserver(showUi)
    }

    fun stopGettingUpdates() {
        uiObservable.clearObserver()
    }
}