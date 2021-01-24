package com.example.tv

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    // event

    init {
        fetchData()
    }

    fun onRefresh() {
        fetchData(true)
    }

    private fun fetchData(isPullToRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}
