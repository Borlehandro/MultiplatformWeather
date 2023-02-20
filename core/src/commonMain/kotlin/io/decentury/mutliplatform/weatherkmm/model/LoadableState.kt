package io.decentury.mutliplatform.weatherkmm.model

@Suppress("Unused")
sealed class LoadableState<out T> {

    object Initial : LoadableState<Nothing>()

    object Loading : LoadableState<Nothing>()

    class Success<T>(val data: T) : LoadableState<T>()

    class Error private constructor(
        val throwable: Throwable?,
        val message: String?,
    ) : LoadableState<Nothing>() {

        constructor(throwable: Throwable) : this(throwable, throwable.message)

        constructor(message: String?) : this(null, message)
    }
}

@Suppress("Unused")
fun <T> LoadableState<T>.isInitial() = this is LoadableState.Initial

@Suppress("Unused")
fun <T> LoadableState<T>.isLoading() = this is LoadableState.Loading

@Suppress("Unused")
fun <T> LoadableState<T>.isError() = this is LoadableState.Error

@Suppress("Unused")
fun <T> LoadableState<T>.isSuccess() = this is LoadableState.Success

@Suppress("Unused")
fun <T> LoadableState<T>.forceData() = (this as? LoadableState.Success<T>)?.data
