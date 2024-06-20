package br.com.study.tmdb_prime_video

import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.study.tmdb_prime_video.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeNavigationTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_homeTabsNavigation() {

        activityRule.scenario.onActivity { activity ->
            val navHostFragment = NavHostFragment.create(R.navigation.tmdb_navigation)
            activity.supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment)
                .commitNow()
        }

        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()))
    }

    @Test
    fun testFragmentNavigation() {
        activityRule.scenario.onActivity { activity ->
            val navController = Navigation.findNavController(activity, R.id.fragmentContainerView)
            navController.navigate(R.id.fragment_home)
        }

        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()))
    }
}