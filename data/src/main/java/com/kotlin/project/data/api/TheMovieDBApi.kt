package com.kotlin.project.data.api

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBApi {
    @GET("3/search/movie?")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("query") searchWord: String,
        @Query("page") page: Int? = 1,
        @Query("language") language: String? = BuildConfig.LANGUAGE
    ): SearchResponse

    @GET("3/search/movie?")
    suspend fun getMovieDetail(
        @Query("api_key") apiKey: String,
        @Query("query") searchWord: String,
        @Query("page") page: Int? = 1,
        @Query("language") language: String? = BuildConfig.LANGUAGE
    ): SearchResponse
}
