package com.example.movietwo.common.network

import com.google.android.gms.common.api.ApiException
import com.google.gson.Gson
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

inline fun <T : Any> safeApiRequest(
    crossinline request:suspend ()->Response<T>
) = flow<Resource<T>> {
    try {
        val response = request.invoke()
        val result = response.body()!!
        if (response.isSuccessful) {
            if (response.body() == null || response.code() == 204) {
                emit(Resource.error("Body is Empty"))
            } else {
                emit(Resource.success(result))
            }
        }
    } catch (e: Exception) {
        when (e) {
            is IOException -> {
                emit(Resource.error("Check your Internet Connection"))
            }
            is HttpException -> {
                val code = e.code()
                val errorBody = e.response()?.errorBody()?.string()
                Timber.d(code.toString())
                Timber.d(errorBody)
                val gsonErrorBody = Gson().fromJson(
                    errorBody,
                    ErrorBody::class.java
                )
                val message = gsonErrorBody.message
                emit(Resource.error( message))
            }
            else -> emit(Resource.error( ""))
        }
    }
}

data class ErrorBody(
    val message: String,
    val status: Int
)
suspend fun <T : Any> handleApiResponse(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiResult.Error(code = e.code(), message = e.message())
    } catch (e: ApiException) {
        ApiResult.Exception(e)
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}
sealed class ApiResult<out T> {
    data class Success<T: Any>(val data: T) : ApiResult<T>()
    data class Error<T: Any>(val code: Int, val message: String?) : ApiResult<T>()
    data class Exception<T: Any>(val e: Throwable) : ApiResult<T>()
}