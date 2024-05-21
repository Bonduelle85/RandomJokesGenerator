package com.example.randomjokesgenerator

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class JokeUi(rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val interaction = onView(
        Matchers.allOf(
            withId(R.id.jokeTextView),
            isAssignableFrom(TextView::class.java),
            rootId,
            rootClass,
        )
    )

    fun check(text: String) {
        interaction.check(matches(withText(text)))
    }
}