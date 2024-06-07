package br.com.study.tmdb_prime_video

import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import br.com.study.tmdb_prime_video.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import br.com.study.tmdb_prime_video.home.R
import br.com.study.tmdb_prime_video.home.presentation.HomeFragment

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeNavigationTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_homeTabsNavigation() {

        with(launchFragment<HomeFragment>()) {
            onFragment { fragment ->
                onView(withId(R.id.iv_app_logo_topbar))
                    .check(matches(isDisplayed()))
            }
        }
    }
}