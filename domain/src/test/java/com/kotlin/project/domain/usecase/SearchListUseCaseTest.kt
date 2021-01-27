package com.kotlin.project.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.project.data.model.TheMovieDBResult
import com.kotlin.project.data.model.failureResponse
import com.kotlin.project.data.model.successResponse
import com.kotlin.project.testData.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("TestFunctionName")
@ExperimentalCoroutinesApi
class SearchListUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    lateinit var searchListUseCase: SearchListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "Star Wars"
        coEvery { searchListUseCase.searchList(any(), any()) } returns
            TheMovieDBResult.Success(TestData.testSearchResponse)
        // act
        searchListUseCase.searchList(ak, word).successResponse?.let {
            println("check_data:${it.results.size}")
            assert(it.results.size == TestData.resultsSize)
        }
    }

    @Test
    fun execute_Error() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "Star Wars"
        val throwable = Throwable(TestData.errorMessage)
        coEvery { searchListUseCase.searchList(any(), any()) } returns
            TheMovieDBResult.Failure(throwable)
        // act
        searchListUseCase.searchList(ak, word).failureResponse?.let {
            println("check_data:${it.message}")
            assert(it.message.equals(TestData.errorMessage))
        }
    }

    @Test
    fun execute_Tv_Success() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "鬼滅"
        coEvery { searchListUseCase.searchTvList(any(), any()) } returns
            TheMovieDBResult.Success(TestData.testSearchResponseForTv)
        // act
        searchListUseCase.searchTvList(ak, word).successResponse?.let {
            println("check_data:${it.results.size}")
            assert(it.results.size == TestData.resultsSizeForTv)
        }
    }

    @Test
    fun execute_Tv_Error() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "鬼滅"
        val throwable = Throwable(TestData.errorMessage)
        coEvery { searchListUseCase.searchTvList(any(), any()) } returns
            TheMovieDBResult.Failure(throwable)
        // act
        searchListUseCase.searchTvList(ak, word).failureResponse?.let {
            println("check_data:${it.message}")
            assert(it.message.equals(TestData.errorMessage))
        }
    }
}
