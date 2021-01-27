package com.example.core.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.core.R
import com.example.core.databinding.ActivityCoreBinding
import dagger.android.support.DaggerAppCompatActivity

class CoreActivity : DaggerAppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private lateinit var binding: ActivityCoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_core)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.navView.setupWithNavController(navController)
//        setBottomNavigationListener(navController)
        setupBottomNavigationBar()
        setBottomNavigationListener()
    }

    private fun setupBottomNavigationBar() {
        binding.navView.setupWithNavController(navController)
    }

    private fun setBottomNavigationListener() {
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_search -> {
                    navController.navigate(R.id.search_nav_graph)
                }
                R.id.navigation_movie -> {
                    navController.navigate(R.id.movie_nav_graph)
                }
                R.id.navigation_tv -> {
                    navController.navigate(R.id.tv_nav_graph)
                }
            }
            true
        }
    }
}
