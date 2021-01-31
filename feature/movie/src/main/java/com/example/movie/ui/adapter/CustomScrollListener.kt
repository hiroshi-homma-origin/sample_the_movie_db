package com.example.movie.ui.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

open class CustomScrollListener(
    private val mLinearLayoutManager: LinearLayoutManager
) : OnScrollListener() {
    var firstVisibleItem = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    private var currentPage: Int = 1
    private var previousTotal = 0
    private var loading = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleItemCount) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    open fun onLoadMore(currentPage: Int) {}
}
