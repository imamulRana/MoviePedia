package inc.anticbyte.moviepedia.utils

sealed class RequestState<out T> {
    data object Loading : RequestState<Nothing>()
    data class Error(val message: String) : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
}