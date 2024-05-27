package com.example.randomjokesgenerator

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

class JokePage(
    private val category: String,
    private val joke: String
) {

    private val rootId: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val parent: Matcher<View> = withParent(isAssignableFrom(LinearLayout::class.java))

    private val categoryUi = CategoryUi(rootId, parent)
    private val jokeUi = JokeUi(rootId, parent)
    private val nextUi = NextUi(rootId, parent)
    private val downloadUi = DownloadUi(rootId, parent)

    fun checkStateIsJoke() {
        categoryUi.check(category)
        jokeUi.check(joke)
        nextUi.checkStateIsJoke()
        downloadUi.checkStateIsJoke()
    }

    fun checkStateIsNoMoreJokes() {
        nextUi.checkStateIsNoMoreJokes()
        downloadUi.checkStateIsNoMoreJokes()
    }

    fun clickNextJoke() {
        nextUi.click()
    }

    fun clickAgain() {
        downloadUi.click()
    }
}