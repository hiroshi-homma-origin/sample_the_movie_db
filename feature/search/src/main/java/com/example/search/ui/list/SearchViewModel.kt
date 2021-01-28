package com.example.search.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.search.BuildConfig
import com.example.search.R.string
import com.kotlin.project.data.entities.transform
import com.kotlin.project.data.model.result.TheMovieDBResult
import com.kotlin.project.data.model.result.failureResponse
import com.kotlin.project.data.model.search.SearchMovieData
import com.kotlin.project.data.model.search.transform
import com.kotlin.project.data.model.status.TheMovieDBStatus
import com.kotlin.project.data.model.status.TheMovieDBStatus.Failure
import com.kotlin.project.data.model.status.TheMovieDBStatus.Loading
import com.kotlin.project.data.model.status.TheMovieDBStatus.ReLoading
import com.kotlin.project.data.model.status.TheMovieDBStatus.Success
import com.kotlin.project.domain.usecase.ResultMovieDataUseCase
import com.kotlin.project.domain.usecase.SearchListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    application: Application,
    private val searchListUseCase: SearchListUseCase,
    private val resultMovieDataUseCase: ResultMovieDataUseCase
) : AndroidViewModel(application), LifecycleObserver {

    // member variables
    private val context = getApplication<Application>().applicationContext
    var currentPage = 1
    var totalPage = 1
    var totalResults = 1

    // status
    private val _status = MutableLiveData<TheMovieDBStatus>()
    val status: LiveData<TheMovieDBStatus> = _status

    // data
    private val _currentResultText = MutableLiveData<String>()
    val currentResultText: LiveData<String> = _currentResultText

    private val _list = MediatorLiveData<List<SearchMovieData>>()
    val list: LiveData<List<SearchMovieData>> = _list

    init {
        fetchData()
    }

    fun refresh() {
        currentPage = 1
        fetchData(isPullToRefresh = true)
    }

    fun addPage(addPage: Int) {
        currentPage = addPage
        fetchData(addPage = addPage)
    }

    private fun fetchData(
        isPullToRefresh: Boolean = false,
        addPage: Int = 1,
        key: String = "Star Wars"
    ) {
        val text = context.getString(string.title_search) + "(" + addPage + " / " + totalPage + ")"
        _status.postValue(if (isPullToRefresh) ReLoading else Loading)
        viewModelScope.launch(Dispatchers.IO) {
            when (val r = searchListUseCase.searchList(BuildConfig.APIKEY, key, addPage)) {
                is TheMovieDBResult.Success -> {
                    totalPage = r.data.totalPages
                    totalResults = r.data.totalResults

                    insertMovieData(r.data.results)

                    _currentResultText.postValue(text)
                    when {
                        isPullToRefresh || addPage <= 1 -> _list.postValue(r.data.results)
                        else -> {
                            val addList = _list.value as MutableList<SearchMovieData>
                            addList.addAll(r.data.results)
                            _list.postValue(addList)
                        }
                    }
                    _status.postValue(Success)
                }
                is TheMovieDBResult.Failure -> {
                    r.failureResponse?.let {
                        Timber.d("check_error:${it.message}")
                        _status.postValue(Failure)
                        checkRoomData()
                    }
                }
            }
        }
    }

    private suspend fun getMovieDataSize() = resultMovieDataUseCase.getMovie().size

    private fun insertMovieData(results: ArrayList<SearchMovieData>) {
        viewModelScope.launch(Dispatchers.IO) {
            if (resultMovieDataUseCase.getMovie().size < totalResults) {
                results.forEach {
                    resultMovieDataUseCase.insert(it.transform())
                }
            }
        }
    }

    private fun checkRoomData() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("check_data1:${getMovieDataSize()}")
            if (resultMovieDataUseCase.getMovie().isNotEmpty()) {
                val list = resultMovieDataUseCase.getMovie().map { it.transform() }
                _list.postValue(list)
            }
        }
    }
}
