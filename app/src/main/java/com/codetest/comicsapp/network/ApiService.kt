package com.codetest.comicsapp.network

import com.codetest.comicsapp.feature.model.ComicModel
import retrofit2.http.GET

interface ApiService {

    @GET("info.0.json")
    suspend fun getCurrentComicInfo() : ComicModel
}