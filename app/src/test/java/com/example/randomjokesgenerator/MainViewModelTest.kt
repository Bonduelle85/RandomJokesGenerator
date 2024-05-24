package com.example.randomjokesgenerator

import com.example.randomjokesgenerator.main.data.MainRepository
import com.example.randomjokesgenerator.main.presentation.MainViewModel
import com.example.randomjokesgenerator.main.presentation.Screen
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {

    @Test
    fun test() {
        val viewModel = MainViewModel(FakeRepository())
        var actual = viewModel.init(true)
        assertEquals(FakeScreen, actual)

        actual = viewModel.init(false)
        assertEquals(Screen.Empty, actual)

    }
}

private class FakeRepository : MainRepository {

    override fun lastSavedScreen(): Screen {
        return FakeScreen
    }
}

private object FakeScreen : Screen