package com.example.randomjokesgenerator

import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class NewUi(rootId: Matcher<View>, parent: Matcher<View>) {

    private val interaction = onView(
        allOf(
            withId(R.id.newButton),
            withText(R.string.newJokes),
            isAssignableFrom(Button::class.java),
            rootId,
            parent
        )
    )


    fun checkStateIsJoke() {
        interaction.check(matches(isEnabled()))
    }

    fun checkStateIsAgain() {
        interaction.check(matches(isNotEnabled()))
    }

    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}