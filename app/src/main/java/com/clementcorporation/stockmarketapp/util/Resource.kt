package com.clementcorporation.stockmarketapp.util

sealed class Resource<T>(val data: T? = null, val message: String? =null){
    class Success<T>(data: T): Resource<T>(data = data)
    class Loading<T>: Resource<T>()
    class Failure<T>(message: String = "Failed to retrieve data", data: T? = null):
        Resource<T>(
            data = data,
            message = message
        )
}
