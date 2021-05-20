package com.codetest.comicsapp.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codetest.comicsapp.common.Resource
import com.codetest.comicsapp.feature.model.ComicModel
import com.codetest.comicsapp.feature.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private val _currentComicInfo: MutableLiveData<Resource<ComicModel>> = MutableLiveData()
    val currentComic: LiveData<Resource<ComicModel>>
        get() = _currentComicInfo

    fun getCurrentComicInfo() {
        viewModelScope.launch {
            val result = repository.getCurrentComicInfo()
            setCurrentComicInfo(result)
        }
    }

    private fun setCurrentComicInfo(result: Resource<ComicModel>) {
        _currentComicInfo.value = result
    }
}