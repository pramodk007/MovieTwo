package com.example.movietwo.common.uitls

import com.example.movietwo.BuildConfig

object Constants {

    object Remote{

        const val BASE_URL = BuildConfig.BASE_URL
        const val API_KEY = BuildConfig.API_KEY
        const val IMG_BASE_URL = "https://image.tmdb.org/t/p/"
        const val DEFAULT_BACKDROP_URL =
            "http://placehold.jp/36/cccccc/aaaaaa/480x270.png?text=Awesome%20Poster%20Here"
        const val DEFAULT_POSTER_URL =
            "http://placehold.jp/48/cccccc/aaaaaa/320x480.png?text=Awesome%20Poster%20Here"

        object UrlEndpoint {
            const val END_POINT_POPULAR = "movie/popular"
        }
        object Parameter{
            const val QUERY_PARAMETER_REGION = "US"
        }
    }

    object Local{
        const val DATABASE_NAME = "todo.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "todo_tbl"
    }

    object Paging {
        const val STARTING_PAGE = 1
        const val LOAD_SIZE = 25
    }
    object Splash {
        const val SPLASH_DURATION = 2500L //in millis
    }

    object NetworkErrors {
        const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
        const val UNABLE_TODO_OPERATION_WO_INTERNET = "Can't do that operation without an internet connection"
        const val ERROR_CHECK_NETWORK_CONNECTION = "Check network connection."
        const val NETWORK_ERROR_UNKNOWN = "Unknown network error"
        const val NETWORK_ERROR = "Network error"
        const val NETWORK_ERROR_TIMEOUT = "Network timeout"
        const val NETWORK_DATA_NULL = "Network data is null"
    }
}