package com.rightcode.baseproject.data.remote

import androidx.annotation.MainThread
import com.rightcode.baseproject.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response


@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT> {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State
        emit(State.loading())

        // Fetch latest posts from remote
        val apiResponse = fetchFromRemote()

        println("NetworkBoundRepository 23 - User : $apiResponse")
        println("NetworkBoundRepository 23 - User : ${apiResponse?.body()}")
        // Check for response validation
        if (apiResponse.isSuccessful && apiResponse.body() != null) {
            emit(State.success<RESULT>(apiResponse.body()!!))
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(apiResponse.message()))
        }
    }.catch { e ->
        println("NetworkBoundRepository 32 - User : ${e.message}")
        println("NetworkBoundRepository 33 - User : ${e.printStackTrace()}")
        // Exception occurred! Emit error
        emit(State.error("${e.message}"))
        e.printStackTrace()
    }

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}
