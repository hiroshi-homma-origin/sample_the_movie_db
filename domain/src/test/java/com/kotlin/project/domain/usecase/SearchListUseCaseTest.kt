package com.kotlin.project.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import com.kotlin.project.data.model.failure.Failure
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
            Ok(TestData.testSearchResponse)
        // act
        searchListUseCase.searchList(ak, word)
            .mapBoth(
                success = { println("success:$it") },
                failure = { println("failure:${it.message}") }
            )
    }

    @Test
    fun execute_Error() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "Star Wars"
        coEvery { searchListUseCase.searchList(any(), any()) } returns Err(Failure)
        // act
        searchListUseCase.searchList(ak, word)
            .mapBoth(
                success = { println("success:$it") },
                failure = { println("failure:${it.message}") }
            )
    }

    @Test
    fun execute_Tv_Success() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "鬼滅"
        coEvery { searchListUseCase.searchTvList(any(), any()) } returns
            Ok(TestData.testSearchResponseForTv)
        searchListUseCase.searchTvList(ak, word)
            .mapBoth(
                success = { println("success:$it") },
                failure = { println("failure:${it.message}") }
            )
    }

    @Test
    fun execute_Tv_Error() = testDispatcher.runBlockingTest {
        // arrange
        val ak = "5a8b983d0a32c33b16d6db1c658e7e1d"
        val word = "鬼滅"
        coEvery { searchListUseCase.searchTvList(any(), any()) } returns Err(Failure)
        // act
        searchListUseCase.searchTvList(ak, word)
            .mapBoth(
                success = { println("success:$it") },
                failure = { println("failure:${it.message}") }
            )
    }
}
