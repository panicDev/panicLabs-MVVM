package id.panicLabs.core.repository

import android.arch.lifecycle.MutableLiveData
import id.panicLabs.core.db.AppDb
import id.panicLabs.core.di.annotation.Mockable
import id.panicLabs.core.retrofit.api.CoreApi
import id.panicLabs.core.retrofit.responses.SectionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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
@Mockable
class CoreRepository(private val coreApi: CoreApi, val appDb: AppDb) {

    private val data = MutableLiveData<SectionResponse>()

    private val error = MutableLiveData<String>()

    private var disposable = Disposables.disposed()

    fun observeSection() = data

    fun observeError() = error

    fun fetchSection(section: String) {
        coreApi.getSection(section).delay(3,TimeUnit.SECONDS)
                .timeout(8,TimeUnit.SECONDS)
//                .doAfterTerminate { disposable.dispose() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            println("CoreRepository.fetchSection Success $it")
                            data.value = it
                        },
                        {
                            println("CoreRepository.fetchSection Error ${it.message}")
                            error.value = it.message ?: "Unknown Error"
                        }
                )

    }
}
