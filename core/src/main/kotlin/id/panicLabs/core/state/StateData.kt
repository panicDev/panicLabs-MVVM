package id.panicLabs.core.state

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
data class StateData<out T>(val data: T? = null, val status: Status = Status.LOADING, val errorMessage: String = "") {

    companion object {
        fun <T> success(data: T): StateData<T> = StateData(data, Status.SUCCESS)
        fun <T> failure(errorMessage: String): StateData<T> = StateData(null, Status.ERROR, errorMessage)
        fun <T> loading(): StateData<T> = StateData(null, Status.LOADING)
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}