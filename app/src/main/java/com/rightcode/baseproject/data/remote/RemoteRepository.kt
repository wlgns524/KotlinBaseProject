package com.rightcode.baseproject.data.remote

import com.rightcode.baseproject.data.remote.api.ApiService
import com.rightcode.baseproject.model.State
import com.rightcode.baseproject.model.remote.TryList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getTest(): Flow<State<TryList>> {
        return object : NetworkBoundRepository<TryList>() {
            override suspend fun fetchFromRemote(): Response<TryList> =
                apiService.getPosts("api-v200/rest/display/experience/delivery?page=1")
        }.asFlow()
    }

//    suspend fun getTest(): Response<TryList> = apiService.getPosts("api-v200/rest/display/experience/delivery?page=1")

}
