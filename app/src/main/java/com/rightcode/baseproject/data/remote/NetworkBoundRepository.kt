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

        val remoteResult = apiResponse
        println("NetworkBoundRepository 23 - User : ${remoteResult}")
        println("NetworkBoundRepository 23 - User : ${remoteResult?.body()}")
        // Check for response validation
        if (remoteResult?.isSuccessful!! && remoteResult.body() != null) {
            emit(State.success<RESULT>(remoteResult.body()!!))
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(remoteResult.message()))
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
