package com.example.search.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search.adapter.CustomScrollListener
import com.example.search.adapter.SearchResultsRecyclerViewAdapter
import com.example.search.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val searchViewModel: SearchViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewModel = searchViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        lifecycle.addObserver(searchViewModel)
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = SearchResultsRecyclerViewAdapter(searchViewModel)
            setCustomScrollListener()
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        binding.swipeRefresh.setOnRefreshListener {
            binding.recyclerView.adapter?.notifyDataSetChanged()
            setCustomScrollListener(true)
            searchViewModel.refresh()
        }
        postponeEnterTransition()
        view.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun observe() {
        searchViewModel.list.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter?.notifyItemInserted(it.size + 1)
        }
    }

    private fun setCustomScrollListener(isRefresh: Boolean = false) {
        binding.recyclerView.apply {
            clearOnScrollListeners()
            addOnScrollListener(object : CustomScrollListener(
                layoutManager as LinearLayoutManager,
                isRefresh,
                searchViewModel.currentPage
            ) {
                    override fun onLoadMore(currentPage: Int) {
                        if (currentPage <= searchViewModel.totalPage) {
                            searchViewModel.addPage(currentPage)
                        }
                    }
                })
        }
    }
}
