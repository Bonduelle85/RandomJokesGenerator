package com.example.randomjokesgenerator.joke

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.randomjokesgenerator.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class CategoryUi(rootId: Matcher<View>, rootClass: Matcher<View>) {

    private val interaction = onView(
        Matchers.allOf(
            withId(R.id.categoryTextView),
            isAssignableFrom(TextView::class.java),
            rootId,
            rootClass,
        )
    )

    fun check(text: String) {
        interaction.check(matches(withText(text)))
    }
}