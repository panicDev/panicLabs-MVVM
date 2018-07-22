package id.panicLabs.core.di.module

import dagger.Module
import dagger.Provides
import id.panicLabs.core.retrofit.api.CoreApi
import retrofit2.Retrofit

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Module
class RetrofitServiceModule {

    @Provides
    fun provideCoreAPI(retrofit: Retrofit): CoreApi {
        return retrofit.create(CoreApi::class.java)
    }

}

