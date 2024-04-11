package br.com.study.tmdb_prime_video.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import br.com.study.tmdb_prime_video.R
import br.com.study.tmdb_prime_video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            binding.bnMenu, navController
        )

        binding.bnMenu.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.fragment_home -> {

                    navController.navigate(R.id.fragment_home)
                    true
                }

                R.id.fragment_store -> {
                    navController.navigate(R.id.fragment_store)
                    true
                }

                R.id.fragment_tv -> {
                    navController.navigate(R.id.fragment_tv)
                    true
                }

                R.id.fragment_downloads -> {
                    navController.navigate(R.id.fragment_downloads)
                    true
                }

                R.id.fragment_search -> {
                    navController.navigate(R.id.fragment_search)
                    true
                }
                else -> false
            }
        }

    }
}