package com.example.randomjokesgenerator

import com.example.randomjokesgenerator.load.data.LoadRepository
import com.example.randomjokesgenerator.load.data.LoadResult
import com.example.randomjokesgenerator.load.presentation.LoadUiState
import com.example.randomjokesgenerator.load.presentation.LoadViewModel
import com.example.randomjokesgenerator.load.presentation.UiObservable
import com.example.randomjokesgenerator.main.presentation.RunAsync
import org.junit.Assert.assertEquals
import org.junit.Test

class LoadViewModelTest {

    /**
     * 1) progress -> error
     * 2) retry
     * 3) progress -> success
     */

    @Test
    fun test() {
        val repository = FakeLoadRepository()
        val runAsync = FakeRunAsync()
        val showUi = FakeUiObservable()
        val viewModel = LoadViewModel(
            uiObservable = showUi,
            repository = repository,
            runAsync =  runAsync)


        // first run -> Progress, Error
        viewModel.init(firstRun = true)

        assertEquals(true, repository.saveLastScreenIsCalled)

        assertEquals(LoadUiState.Progress, showUi.uiStateList[0])
        assertEquals(LoadUiState.Error(message = "failed to fetch data"), showUi.uiStateList[1])

        // retry -> Progress, Success
        viewModel.retry()

        assertEquals(LoadUiState.Progress, showUi.uiStateList[2])
        assertEquals(LoadUiState.Success, showUi.uiStateList[3])

        // change configuration (rotate screen)
        assertEquals(4, showUi.uiStateList.size)
        viewModel.init(firstRun = false)
        assertEquals(4, showUi.uiStateList.size)

    }
}

class FakeLoadRepository : LoadRepository {

    private var returnSuccess = false
    var saveLastScreenIsCalled = false

    override fun load(): LoadResult {
        return if (returnSuccess)
            LoadResult.Success
        else {
            returnSuccess = true
            LoadResult.Error(message = "failed to fetch data")
        }
    }

    override fun saveLastScreenIsLoad() {
        saveLastScreenIsCalled = true
    }
}

class FakeRunAsync : RunAsync {

    override fun <T : Any> runAsync(background: () -> T, ui: (T) -> Unit) {
        val result = background.invoke()
        ui.invoke(result)
    }
}

class FakeUiObservable : UiObservable {
    val uiStateList = mutableListOf<LoadUiState>()

    override fun updateObserver(observer: (LoadUiState) -> Unit) {

    }

    override fun clearObserver() {

    }

    override fun updateUiState(uiState: LoadUiState) {
        uiStateList.add(uiState)
    }
}