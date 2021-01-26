package com.example.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.model.ResultsData
import com.kotlin.project.data.model.TheMovieDBResult
import com.kotlin.project.data.model.TheMovieDBStatus
import com.kotlin.project.data.model.TheMovieDBStatus.Failure
import com.kotlin.project.data.model.TheMovieDBStatus.Loading
import com.kotlin.project.data.model.TheMovieDBStatus.ReLoading
import com.kotlin.project.data.model.TheMovieDBStatus.Success
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

    private val context = getApplication<Application>().applicationContext

    var currentPage = 1
    var totalPage = 1

    // status
    private val _status = MutableLiveData<TheMovieDBStatus>()
    val status: LiveData<TheMovieDBStatus> = _status

    private val _currentResultText = MutableLiveData<String>()
    val currentResultText: LiveData<String> = _currentResultText

    // data
    private val _list = MediatorLiveData<List<ResultsData>>()
    val list: LiveData<List<ResultsData>> = _list

    init {
        fetchData()
    }

    fun onRefresh() {
        fetchData(isPullToRefresh = true)
    }

    fun onAddPage(addPage: Int) {
        fetchData(addPage = addPage)
    }

    private fun fetchData(isPullToRefresh: Boolean = false, addPage: Int = 1) {
        _status.postValue(if (isPullToRefresh) ReLoading else Loading)
        viewModelScope.launch(Dispatchers.IO) {
            when (
                val r =
                    getMovieListUseCase.getMovieList(BuildConfig.APIKEY, "Star Wars", addPage)
            ) {
                is TheMovieDBResult.Success -> {
                    currentPage = addPage
                    totalPage = r.data.totalPages

                    _currentResultText.postValue(
                        context.getString(R.string.title_search) +
                            "GetData (" + addPage + " / " + r.data.totalPages + ")"
                    )
                    if (!isPullToRefresh && currentPage > 1) {
                        val addList = _list.value as MutableList<ResultsData>
                        addList.addAll(r.data.results)
                        _list.postValue(addList)
                    } else {
                        _list.postValue(r.data.results)
                    }
                    _status.postValue(Success)
                }
                is TheMovieDBResult.Failure -> {
                    r.failureResponse?.let {
                        Timber.d("check_error:${it.message}")
                        _status.postValue(Failure)
                    }
                }
            }
        }
    }
}
