package com.example.flickrimagesearch.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher

@ExperimentalCoroutinesApi
class TestContextProvider : CoroutineContextProvider() {
    //val testCoroutineDispatcher = StandardTestDispatcher()

    //override val Main: CoroutineContext = testCoroutineDispatcher
   //override val IO: CoroutineContext = testCoroutineDispatcher
}
