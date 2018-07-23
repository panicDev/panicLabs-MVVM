package id.panicLabs.core.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.parser.Section
import id.panicLabs.core.db.AppDb
import id.panicLabs.core.retrofit.api.CoreApi
import id.panicLabs.core.retrofit.responses.SectionResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 *
 * I've a small naming convention here:
 * - I use the fetch prefix for a function that does a server-side call and returns the result.
 * - I use the get prefix for a function that does a DB query and returns the result.
 * - I use the retrive prefix for a function that does a server-side call and returns the database
 * Flowable to observe the data.
 */
class CoreRepository(private val coreApi: CoreApi, val appDb: AppDb) {

    val networkStatus =  MutableLiveData<String>()

    fun fetchSection(section: String): LiveData<List<SectionResponse.Post>> {
        return LiveDataReactiveStreams.fromPublisher { getSection(section) }
    }

    private fun getSection(section: String): Single<SectionResponse> {
        return coreApi.getSection(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
