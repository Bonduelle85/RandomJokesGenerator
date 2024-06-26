package com.example.randomjokesgenerator.load

import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.randomjokesgenerator.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

class RetryUi(rootId: Matcher<View>, parent: Matcher<View>) {

    private val id: Int = R.id.retryButton

    private val interaction = onView(
        allOf(
            withId(id),
            withText(R.string.retry),
            isAssignableFrom(Button::class.java),
            rootId,
            parent
        )
    )

    fun checkVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun checkNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}