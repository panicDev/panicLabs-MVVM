package id.panicLabs.core.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Observer
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.reactivestreams.Publisher

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
fun <T> Flowable<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this)
fun <T> Maybe<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable())
fun <T> Single<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable())
fun <T> Observable<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable(io.reactivex.BackpressureStrategy.BUFFER))
fun <T> Publisher<T>.toLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this)

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { action(it) } })
}