package com.example.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.search.adapter.CustomScrollListener
import com.example.search.adapter.SearchResultsRecyclerViewAdapter
import com.example.search.databinding.FragmentSearchBinding
import timber.log.Timber
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
            adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = SearchResultsRecyclerViewAdapter(searchViewModel)
            scrollCheck()
        }
        binding.swipeRefresh.setOnRefreshListener {
            scrollCheck()
            searchViewModel.onRefresh()
        }
    }

    private fun observe() {
        searchViewModel.list.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter?.notifyItemInserted(it.size + 1)
        }
    }

    private fun scrollCheck() {
        binding.recyclerView.addOnScrollListener(object : CustomScrollListener(
            binding.recyclerView.layoutManager as LinearLayoutManager
        ) {
                override fun onLoadMore(currentPage: Int) {
                    Timber.d("check_data:$currentPage")
                    if (searchViewModel.currentPage < searchViewModel.totalPage) {
                        searchViewModel.onAddPage(currentPage)
                    }
                }
            })
    }
}
