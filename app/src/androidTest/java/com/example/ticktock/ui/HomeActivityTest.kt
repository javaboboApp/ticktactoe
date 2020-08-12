package com.example.ticktock.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.ticktock.R
import kotlinx.android.synthetic.main.activity_home.view.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {


    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun test_check_that_home_activity_is_working_and_visible() {
        onView(withId(R.id.activity_home)).check(matches(isDisplayed()))
    }

    @Test
    fun test_check_bg_tictactoe_is_visible() {
        onView(withId(R.id.bg_tictactoe)).check(matches(isDisplayed()))
    }

    @Test
    fun test_who_is_playing_text_is_visble() {
        onView(withId(R.id.who_player)).check(matches(isDisplayed()))

    }

    @Test
    fun test_who_is_player_img_is_visible() {
        onView(withId(R.id.who_play_img)).check(matches(isDisplayed()))

    }
}