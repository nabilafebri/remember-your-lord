package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils

open class State<T> {
    class Initialize<T> : State<T>()
    class Loading<T> : State<T>()
    data class Success<T>(val data: T?) : State<T>()
    data class Failed<T>(val throwable: Throwable?) : State<T>()

    fun init(): State<T> {
        return Initialize<T>()
    }

    fun loading(): State<T> {
        return Loading<T>()
    }

    fun success(data: T?): State<T> {
        return Success(data)
    }

    fun failed(throwable: Throwable?): State<T> {
        return Failed<T>(throwable)
    }
}