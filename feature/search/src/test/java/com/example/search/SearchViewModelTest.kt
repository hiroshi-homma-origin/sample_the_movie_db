package com.example.search

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.search.ui.list.SearchViewModel
import com.kotlin.project.data.entities.MovieData
import com.kotlin.project.data.entities.transform
import com.kotlin.project.data.model.status.TheMovieDBStatus
import com.kotlin.project.domain.usecase.ResultMovieDataUseCase
import com.kotlin.project.domain.usecase.SearchListUseCase
import com.kotlin.project.testData.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("TestFunctionName")
class SearchViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: SearchViewModel

    @RelaxedMockK
    lateinit var application: Application

    @RelaxedMockK
    lateinit var searchListUseCase: SearchListUseCase

    @RelaxedMockK
    lateinit var resultMovieDataUseCase: ResultMovieDataUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Successful acquisition of data_データの取得に成功`() = runBlocking {
        // arrange
        val observerList = TestObserver<List<MovieData>>()
        val observerStatus = TestObserver<TheMovieDBStatus>()
        viewModel.list.observeForever(observerList)
        viewModel.status.observeForever(observerStatus)
        coEvery {
            resultMovieDataUseCase.getMovie()
        } returns TestData.searchList()
        // act
        viewModel.refresh()
        // assert
        assert(observerStatus.get() == TheMovieDBStatus.ReLoading)
        observerList.await()
        val result = observerList.get()?.map { it.transform() }
        println("check_data1:$result")
        assert(result == TestData.searchList())
        assert(observerStatus.get() == TheMovieDBStatus.Success)
    }
}
