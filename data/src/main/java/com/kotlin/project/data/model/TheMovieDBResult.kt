package com.kotlin.project.data.model

import com.kotlin.project.data.model.TheMovieDBResult.Failure
import com.kotlin.project.data.model.TheMovieDBResult.Success

sealed class TheMovieDBResult<out R> {

    data class Success<out T>(val data: T) : TheMovieDBResult<T>()
    data class Failure(val throwable: Throwable) : TheMovieDBResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$throwable]"
        }
    }
}

val <T> TheMovieDBResult<T>.successResponse: T?
    get() = (this as? Success)?.data

val <T> TheMovieDBResult<T>.failureResponse: Throwable?
    get() = (this as? Failure)?.throwable
