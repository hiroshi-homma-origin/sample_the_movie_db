package com.example.search.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.search.R
import com.example.search.databinding.ItemSearchResultBinding
import com.example.search.ui.list.SearchFragmentDirections
import com.example.search.ui.list.SearchViewModel

class SearchResultsRecyclerViewAdapter(
    private val spritesViewModel: SearchViewModel
) : RecyclerView.Adapter<SearchResultsRecyclerViewAdapter.SearchResultsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsHolder {
        val binding = DataBindingUtil.inflate<ItemSearchResultBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_search_result,
            parent,
            false
        )
        return SearchResultsHolder(binding)
    }

    override fun getItemCount(): Int {
        return spritesViewModel.list.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: SearchResultsHolder, position: Int) {
        val list = spritesViewModel.list.value ?: return
        holder.binding.results = list[position]
        val path = list[position].posterPath ?: ""
        holder.itemView.setOnClickListener {
            holder.binding.mainImage.apply {
                transitionName = path
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchToSearchDetail(
                        transitionName,
                        list[position].id,
                        path
                    ),
                    FragmentNavigatorExtras(this to transitionName)
                )
            }
        }
        holder.binding.favorite.apply {
            isSelected = if (list[position].isFavorite) {
                setImageResource(R.drawable.ic_baseline_check_circle_24)
                true
            } else {
                setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                false
            }
            setOnClickListener {
                isSelected = !isSelected
                when {
                    isSelected -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_24)
                    }
                    else -> {
                        setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
                    }
                }
                spritesViewModel.updateCacheData(list[position].id, isSelected)
            }
        }
    }

    class SearchResultsHolder(val binding: ItemSearchResultBinding) : ViewHolder(binding.root)
}
