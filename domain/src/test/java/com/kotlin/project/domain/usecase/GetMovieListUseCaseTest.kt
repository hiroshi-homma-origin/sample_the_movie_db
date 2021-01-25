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
class GetMovieListUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    @RelaxedMockK
    lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "Star Wars"
        coEvery { getMovieListUseCase.getMovieList(any(), any()) } returns
            TheMovieDBResult.Success(TestData.testSearchResponse)
        // act
        getMovieListUseCase.getMovieList(ak, word).successResponse?.let {
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
        coEvery { getMovieListUseCase.getMovieList(any(), any()) } returns
            TheMovieDBResult.Failure(throwable)
        // act
        getMovieListUseCase.getMovieList(ak, word).failureResponse?.let {
            println("check_data:${it.message}")
            assert(it.message.equals(TestData.errorMessage))
        }
    }
}
