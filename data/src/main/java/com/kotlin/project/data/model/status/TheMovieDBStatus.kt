package com.kotlin.project.data.model.status

sealed class TheMovieDBStatus {
    object Success : TheMovieDBStatus()
    object Failure : TheMovieDBStatus()
    object Loading : TheMovieDBStatus()
    object ReLoading : TheMovieDBStatus()
}
