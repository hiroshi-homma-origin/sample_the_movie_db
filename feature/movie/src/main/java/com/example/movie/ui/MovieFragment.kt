package com.example.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.ui.adapter.CustomScrollListener
import com.example.movie.ui.adapter.MovieFavoritesRecyclerViewAdapter
import javax.inject.Inject

class MovieFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val movieViewModel: MovieViewModel by activityViewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = movieViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycle.addObserver(movieViewModel)
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = MovieFavoritesRecyclerViewAdapter(movieViewModel)
            setCustomScrollListener()
        }
        binding.swipeRefresh.setOnRefreshListener {
            binding.recyclerView.adapter?.notifyDataSetChanged()
            setCustomScrollListener(true)
            movieViewModel.refresh()
        }
        postponeEnterTransition()
        view.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun observe() {
        movieViewModel.list.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun setCustomScrollListener(isRefresh: Boolean = false) {
        binding.recyclerView.apply {
            clearOnScrollListeners()
            addOnScrollListener(
                object : CustomScrollListener(layoutManager as LinearLayoutManager) {
                    override fun onLoadMore(currentPage: Int) {
                        movieViewModel.checkRoomData()
                    }
                }
            )
        }
    }
}
