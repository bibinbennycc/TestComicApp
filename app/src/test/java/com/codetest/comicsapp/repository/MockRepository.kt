package com.codetest.comicsapp.repository

import com.codetest.comicsapp.common.Resource
import com.codetest.comicsapp.feature.model.ComicModel
import com.codetest.comicsapp.feature.repository.AppRepository

class MockRepository : AppRepository {

    var isReturnError = false

    override suspend fun getCurrentComicInfo(): Resource<ComicModel> {
        return if (!isReturnError) {
            Resource.success(ComicModel("Test Title", "test_url", "Test description"))
        } else {
            Resource.error(null, "Something went wrong")
        }
    }
}