package id.panicLabs.core.repository

import android.arch.lifecycle.MutableLiveData
import id.panicLabs.core.db.AppDb
import id.panicLabs.core.retrofit.api.CoreApi

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
class CoreRepository(val coreApi: CoreApi, val appDb: AppDb) {

    val networkStatus =  MutableLiveData<String>()
}
