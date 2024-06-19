package br.com.study.tmdb_prime_video

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.study.tmdb_prime_video.home.R
import br.com.study.tmdb_prime_video.home.presentation.HomeFragment
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeNavigationTest {

    @Test
    fun test_homeTabsNavigation() {

        val mockNavController: NavController = mockk(relaxed = true)

        val titleScenario = launchFragmentInContainer<HomeFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(withId(R.id.iv_app_logo_topbar))
            .check(matches(isDisplayed()))
    }
}