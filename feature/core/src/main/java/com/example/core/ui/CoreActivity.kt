package com.example.core.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.core.R
import com.example.core.databinding.ActivityCoreBinding
import com.example.movie.ui.MovieViewModel
import com.example.search.ui.list.SearchViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class CoreActivity : DaggerAppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private lateinit var binding: ActivityCoreBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val searchViewModel: SearchViewModel by viewModels { factory }
    private val movieViewModel: MovieViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_core)
        searchViewModel.fetchSearchList()
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
                    searchViewModel.checkRoomData()
                    navController.navigate(R.id.search_nav_graph)
                }
                R.id.navigation_movie -> {
                    movieViewModel.checkRoomData()
                    navController.navigate(R.id.movie_nav_graph)
                }
            }
            true
        }
    }
}
