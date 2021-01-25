package com.example.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
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
            lifecycleOwner = viewLifecycleOwner
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        lifecycle.addObserver(searchViewModel)
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observe() {
        searchViewModel.list.observe(viewLifecycleOwner) {
            Timber.d("check_observe_data:$it")
            binding.recyclerView.adapter = SearchResultsRecyclerViewAdapter(searchViewModel)
        }
    }
}
