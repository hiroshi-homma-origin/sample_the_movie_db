package com.kotlin.project.data.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.api.TheMovieDBApi
import com.kotlin.project.testData.TestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Suppress("TestFunctionName")
@ExperimentalCoroutinesApi
class DetailDataRepositoryTest {

    private var mockWebServer = MockWebServer()
    private lateinit var theMovieDBApi: TheMovieDBApi

    @Before
    fun setup() {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.API_READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
        theMovieDBApi = Retrofit.Builder()
            .baseUrl(BuildConfig.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
            .create(TheMovieDBApi::class.java)
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testFetchDetailData() = runBlocking {
        val r = DetailDataRepositoryImpl(theMovieDBApi)
            .detailData(11, BuildConfig.APIKEY)

        println("check_data1:$r")
        println("check_data2:${TestData.dummyDetailDataJapanese}")

        assert(r == TestData.dummyDetailDataJapanese)
    }
}
