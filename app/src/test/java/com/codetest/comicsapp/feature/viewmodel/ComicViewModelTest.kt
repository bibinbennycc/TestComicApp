package com.codetest.comicsapp.feature.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codetest.comicsapp.MainCoroutineRule
import com.codetest.comicsapp.common.Status
import com.codetest.comicsapp.getOrAwaitValueTest
import com.codetest.comicsapp.repository.MockRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ComicViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var comicViewModel: ComicViewModel
    private lateinit var repository: MockRepository

    @Before
    fun setUp() {
        repository = MockRepository()
        comicViewModel = ComicViewModel(repository)
    }

    @Test
    fun testGetCurrentComicInfoReturnsSuccess() =mainCoroutineRule.runBlockingTest {
        comicViewModel.getCurrentComicInfo()
        val result = comicViewModel.currentComic.getOrAwaitValueTest()
        assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun testGetCurrentComicInfoReturnsError() =mainCoroutineRule.runBlockingTest {
        repository.isReturnError = true
        comicViewModel.getCurrentComicInfo()
        val result = comicViewModel.currentComic.getOrAwaitValueTest()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }
}