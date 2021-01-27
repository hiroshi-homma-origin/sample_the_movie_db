package com.kotlin.project.data.repository

import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.data.model.response.DetailResponse
import javax.inject.Inject

interface DetailDataRepository {
    suspend fun detailData(
        path: Int,
        apiKey: String,
        language: String? = BuildConfig.LANGUAGE
    ): DetailResponse
}

internal class DetailDataRepositoryImpl @Inject constructor(
    private val theMovieDBApi: TheMovieDBApi
) : DetailDataRepository {

    override suspend fun detailData(
        path: Int,
        apiKey: String,
        language: String?
    ): DetailResponse = theMovieDBApi.detailData(path, apiKey, language)
}
