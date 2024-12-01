package net.micg.lab5.data

sealed class HttpResponseState<out T> {
    data class Success<out T>(val value: T) : HttpResponseState<T>()
    data class Failure(val message: String) : HttpResponseState<Nothing>()
}
