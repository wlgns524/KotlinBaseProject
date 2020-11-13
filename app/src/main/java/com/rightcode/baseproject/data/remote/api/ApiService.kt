package com.rightcode.baseproject.data.remote.api

import com.rightcode.baseproject.model.remote.TryList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPosts(
        @Url link: String
    ): Response<TryList>

    companion object {
        const val BASE_URL = "http://qa-rest.08liter.com"
    }
}