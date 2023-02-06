package io.decentury.mutliplatform.weatherkmm.android.common

sealed class LoadableState<out T> {

    object Initial : LoadableState<Nothing>()

    object Loading : LoadableState<Nothing>()

    class Success<T>(val data: T) : LoadableState<T>()

    class Error private constructor(
        val throwable: Throwable?,
        val message: String?
    ) : LoadableState<Nothing>() {

        constructor(throwable: Throwable) : this(throwable, throwable.message)

        constructor(message: String?) : this(null, message)
    }
}

fun <T> LoadableState<T>.isInitial() = this is LoadableState.Initial
fun <T> LoadableState<T>.isLoading() = this is LoadableState.Loading
fun <T> LoadableState<T>.isError() = this is LoadableState.Error
fun <T> LoadableState<T>.isSuccess() = this is LoadableState.Success
fun <T> LoadableState<T>.forceData() = (this as? LoadableState.Success<T>)?.data
