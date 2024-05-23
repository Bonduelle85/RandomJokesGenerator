package com.example.randomjokesgenerator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomjokesgenerator.main.presentation.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var loadPage: LoadPage
    private lateinit var jokePage: JokePage

    private fun recreate(){
        activityScenarioRule.scenario.recreate()
    }

    @Before
    fun setup() {
        loadPage = LoadPage()
        jokePage = JokePage("0", "0")
    }

    /**
     * LoadPage, Progress State
     * LoadPage, Error State
     * Нажать “retry”
     * LoadPage, Progress State
     * JokePage, Progress State
     * Click “next joke”
     * JokePage, Joke State
     * Click “new jokes”
     * LoadPage, Progress State
     */

    @Test
    fun testCase() {

        loadPage.checkProgressState()

        loadPage.waitUntilError()
        loadPage.checkErrorState(message = "No internet connection")

        loadPage.clickRetry()
        loadPage.checkProgressState()

        loadPage.waitUntilDisappear()

        jokePage.checkStateIsJoke()
        recreate()
        jokePage.checkStateIsJoke()

        repeat(9) {
            jokePage.clickNextJoke()

            jokePage = JokePage(
                "${it+1}",
                "${it+1}"
            )

            jokePage.checkStateIsJoke()
            recreate()
            jokePage.checkStateIsJoke()
        }

        jokePage.clickNextJoke()
        jokePage.checkStateIsAgain()
        recreate()
        jokePage.checkStateIsAgain()

        jokePage.clickAgain()

        loadPage.checkProgressState()
    }
}