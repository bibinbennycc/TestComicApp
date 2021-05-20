package com.codetest.comicsapp.feature.repository

import com.codetest.comicsapp.common.Resource
import com.codetest.comicsapp.feature.model.ComicModel

interface AppRepository {
    suspend fun getCurrentComicInfo(): Resource<ComicModel>
}