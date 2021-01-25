package com.example.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.model.TheMovieDBResult
import com.kotlin.project.data.model.failureResponse
import com.kotlin.project.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    application: Application,
    private val getMovieListUseCase: GetMovieListUseCase
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
            when (val r = getMovieListUseCase.getMovieList(BuildConfig.APIKEY, "Star Wars")) {
                is TheMovieDBResult.Success -> {
                    Timber.d("check_data:${r.data.results}")
                }
                else -> {
                    r.failureResponse?.let {
                        Timber.d("check_error:${it.message}")
                    }
                }
            }
        }
    }
}
