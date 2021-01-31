package com.example.search.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.entities.MovieData
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
    var fetchCount = 1

    // status
    private val _status = MutableLiveData<TheMovieDBStatus>()
    val status: LiveData<TheMovieDBStatus> = _status

    // data
    private val _currentResultText = MutableLiveData<String>()
    val currentResultText: LiveData<String> = _currentResultText

    private val _list = MediatorLiveData<List<MovieData>>()
    val list: LiveData<List<MovieData>> = _list

    init {
        checkRoomData()
    }

    fun fetchSearchList(){
        fetchSearchData()
    }

    fun refresh() {
        checkRoomData(true)
    }

    private fun fetchSearchData(firstSearchWord: String = "Star Wars") {
        viewModelScope.launch(Dispatchers.IO) {
            searchListUseCase.searchList(BuildConfig.APIKEY, firstSearchWord, fetchCount)
                .mapBoth(
                    success = {
                        if(fetchCount <= it.totalPages && getMovieDataSize() < it.totalResults) {
                            Timber.d("check_insert:$fetchCount")
                            insertMovieData(it.results)
                        }
                    },
                    failure = {
                        Timber.d("check_data2:$it")
                    }
                )
        }
    }

    private fun insertMovieData(results: ArrayList<SearchMovieData>) {
        viewModelScope.launch(Dispatchers.IO) {
            results.forEach {
                resultMovieDataUseCase.insert(it.transform())
            }
        }
        fetchCount++
        fetchSearchData()
        checkRoomData()
    }

    private suspend fun getMovieDataSize() = resultMovieDataUseCase.getMovie().size

    fun checkRoomData(isPullToRefresh: Boolean = false) {
        _status.postValue(if (isPullToRefresh) ReLoading else Loading)
        viewModelScope.launch(Dispatchers.IO) {
            _list.postValue(resultMovieDataUseCase.getMovie())
            if(resultMovieDataUseCase.getMovie().isNotEmpty()){
                _status.postValue(Success)
            }else{
                _status.postValue(Failure)
            }
        }
    }

    fun updateCacheData(id: Int, isSelected: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            resultMovieDataUseCase.updateIsFavorite(id, isSelected)
        }
    }
}
