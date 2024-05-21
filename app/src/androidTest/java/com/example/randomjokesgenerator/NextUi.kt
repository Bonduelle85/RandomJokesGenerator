package com.example.randomjokesgenerator

import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class NextUi(rootId: Matcher<View>, parent: Matcher<View>) {

    private val interaction = onView(
        allOf(
            withId(R.id.nextButton),
            withText(R.string.nextJoke),
            isAssignableFrom(Button::class.java),
            rootId,
            parent
        )
    )

    fun checkStateIsJoke() {
        interaction.check(matches(isNotEnabled()))
    }

    fun checkStateIsAgain() {
        interaction.check(matches(isEnabled()))
    }

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}