package com.example.movie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.model.status.TheMovieDBStatus
import com.kotlin.project.data.model.status.TheMovieDBStatus.Failure
import com.kotlin.project.data.model.status.TheMovieDBStatus.Loading
import com.kotlin.project.data.model.status.TheMovieDBStatus.ReLoading
import com.kotlin.project.data.model.status.TheMovieDBStatus.Success
import com.kotlin.project.domain.usecase.ResultMovieDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    application: Application,
    private val resultMovieDataUseCase: ResultMovieDataUseCase
) : AndroidViewModel(application), LifecycleObserver {

    // status
    private val _status = MutableLiveData<TheMovieDBStatus>()
    val status: LiveData<TheMovieDBStatus> = _status

    // data
    private val _list = MediatorLiveData<List<MovieData>>()
    val list: LiveData<List<MovieData>> = _list

    init {
        checkRoomData()
    }

    fun refresh() {
        checkRoomData(true)
    }

    private suspend fun getFavoriteMovieDataSize() = resultMovieDataUseCase.getFavoriteMovie().size

    fun checkRoomData(isPullToRefresh: Boolean = false) {
        _status.postValue(if (isPullToRefresh) ReLoading else Loading)
        viewModelScope.launch(Dispatchers.IO) {
            _list.postValue(resultMovieDataUseCase.getFavoriteMovie())
            if(resultMovieDataUseCase.getFavoriteMovie().isNotEmpty()){
                _status.postValue(Success)
            }else{
                _status.postValue(Failure)
            }
        }
    }

    fun updateIsFavorite(id: Int, isSelected: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            resultMovieDataUseCase.updateIsFavorite(id, isSelected)
        }
    }
}
