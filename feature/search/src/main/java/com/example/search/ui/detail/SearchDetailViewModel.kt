package com.example.search.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.response.DetailResponse
import com.kotlin.project.domain.usecase.DetailDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SearchDetailViewModel @Inject constructor(
    application: Application,
    private val detailDataUseCase: DetailDataUseCase
) : AndroidViewModel(application), LifecycleObserver {

    // data
    private val _detailData = MediatorLiveData<DetailResponse>()
    val detailData: LiveData<DetailResponse> = _detailData

    private val _movieId = MutableLiveData<Int>()

    fun setMovieId(movieId: Int) {
        _movieId.value = movieId
        fetchData(movieId)
    }

    private fun fetchData(path: Int = 11) {
        viewModelScope.launch(Dispatchers.IO) {
            detailDataUseCase.detailData(path, BuildConfig.APIKEY)
                .mapBoth(
                    success = {
                        _detailData.postValue(it)
                    },
                    failure = {
                        Timber.d("check_error:${it.message}")
                    }
                )
        }
    }
}
