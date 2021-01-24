package com.kotlin.project.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.project.data.repository.GetMovieListRepository
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
    lateinit var getMovieListRepository: GetMovieListRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "Star Wars"
        coEvery {
            getMovieListRepository.getMovieList(
                ak,
                word
            )
        } returns TestData.testSearchResponse
        // act
        val u = GetMovieListUseCaseImpl(getMovieListRepository).getMovieList(ak, word)
        assert(u.results.size == TestData.resultsSize)
        assert(u.totalPages == TestData.totalPages)
        assert(u.totalResults == TestData.totalResults)
        assert(u.results == TestData.testSearchResponse.results)
    }
}
