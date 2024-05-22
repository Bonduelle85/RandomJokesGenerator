package com.example.randomjokesgenerator

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

class LoadPage {

    private val rootId: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val parent: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    private val errorUi = ErrorUi(rootId, parent)
    private val progressUi = ProgressUi(rootId, parent)
    private val retryUi = RetryUi(rootId, parent)

    fun checkProgressState() {
        progressUi.checkVisible()
        errorUi.checkNotVisible()
        retryUi.checkNotVisible()
    }

    fun waitUntilError() {
        errorUi.waitUntilVisible()
    }

    fun checkErrorState(message: String) {
        progressUi.checkNotVisible()
        retryUi.checkVisible()
        errorUi.checkVisible(message = message)
    }

    fun clickRetry() {
        retryUi.click()
    }

    fun waitUntilDisappear() {
        Espresso.onView(isRoot()).perform(Wait(1100))
    }
}