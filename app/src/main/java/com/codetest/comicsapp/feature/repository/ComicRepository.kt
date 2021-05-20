package com.codetest.comicsapp.feature.repository

import android.util.Log
import com.codetest.comicsapp.common.Resource
import com.codetest.comicsapp.feature.model.ComicModel
import com.codetest.comicsapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComicRepository(private val apiService: ApiService): AppRepository {
    override suspend fun getCurrentComicInfo(): Resource<ComicModel> {
        var response: Resource<ComicModel>
        try {
            withContext(Dispatchers.IO) {
                val result = apiService.getCurrentComicInfo()
                response = Resource.success(result)
            }
        } catch (exception: Exception) {
            response = Resource.error(null, "Something went wrong")
            Log.e("Comic App Error", exception.toString())
        }
        return response
    }
}