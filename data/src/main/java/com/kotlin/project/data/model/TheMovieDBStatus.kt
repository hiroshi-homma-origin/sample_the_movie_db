package com.kotlin.project.data.model

sealed class TheMovieDBStatus {
    object Success : TheMovieDBStatus()
    object Failure : TheMovieDBStatus()
    object Loading : TheMovieDBStatus()
    object ReLoading : TheMovieDBStatus()
}
