package com.example.flickrimagesearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.flickrimagesearch.mocks.FlickerRepositoryMock
import com.example.flickrimagesearch.mocks.Mocks
import com.example.flickrimagesearch.ui.view.main.MainViewModel
import com.example.flickrimagesearch.util.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MainUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val flickrRepository = FlickerRepositoryMock()

    @Before
    fun setUp() {

    }

    @Test
    fun `getFeeds should update feeds and loading state on success`() = runTest {
        flickrRepository.changeResponseToSuccess()
        val viewModel = MainViewModel(CoroutineContextProvider(), flickrRepository)
        viewModel.getFeeds("porcupine")
        assertEquals(viewModel.loading.value, true)
        delay(1000)
        assertEquals(viewModel.loading.value, false)
        assertEquals(viewModel.feed.value, Mocks.flickrItems)
    }

    @Test
    fun `getFeeds should update loading state on error`() = runTest {
        flickrRepository.changeResponseToError()
        val viewModel = MainViewModel(CoroutineContextProvider(), flickrRepository)
        viewModel.getFeeds("porcupine")
        assertEquals(viewModel.loading.value, true)
        delay(1000)
        assertEquals(viewModel.loading.value, false)
        assertEquals(viewModel.feed.value, null)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}