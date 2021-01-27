package com.kotlin.project.data.api

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.model.response.DetailResponse
import com.kotlin.project.data.model.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {
    @GET("3/search/movie")
    suspend fun searchList(
        @Query("api_key") apiKey: String,
        @Query("query") searchWord: String,
        @Query("page") page: Int? = 1,
        @Query("language") language: String? = BuildConfig.LANGUAGE
    ): SearchResponse

    @GET("3/search/tv")
    suspend fun searchTvList(
        @Query("api_key") apiKey: String,
        @Query("query") searchWord: String,
        @Query("page") page: Int? = 1,
        @Query("language") language: String? = BuildConfig.LANGUAGE
    ): SearchResponse

    @GET("3/movie/{id}")
    suspend fun detailData(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = BuildConfig.LANGUAGE
    ): DetailResponse
}
