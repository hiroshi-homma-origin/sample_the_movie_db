package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movie.R
import com.example.movie.databinding.ItemMovieResultBinding
import com.example.movie.ui.MovieViewModel
import com.example.movie.ui.adapter.MovieFavoritesRecyclerViewAdapter.MovieHolder
import timber.log.Timber

class MovieFavoritesRecyclerViewAdapter(
    private val movieViewModel: MovieViewModel
) : RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = DataBindingUtil.inflate<ItemMovieResultBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_result,
            parent,
            false
        )
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieViewModel.list.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val list = movieViewModel.list.value ?: return
        holder.binding.results = list[position]
        holder.binding.favorite.apply {
            Timber.d("check_data:${list[position].isFavorite}")
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
                movieViewModel.updateIsFavorite(list[position].id, isSelected)
            }
        }
    }

    class MovieHolder(val binding: ItemMovieResultBinding) : ViewHolder(binding.root)
}
