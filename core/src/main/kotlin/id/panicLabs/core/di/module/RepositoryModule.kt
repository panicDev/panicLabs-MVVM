package id.panicLabs.core.di.module

import dagger.Module
import dagger.Provides
import id.panicLabs.core.db.AppDb
import id.panicLabs.core.repository.CoreRepository
import id.panicLabs.core.retrofit.api.CoreApi
import javax.inject.Singleton

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCoreRepository(coreApi: CoreApi, appDb: AppDb) : CoreRepository {
        return CoreRepository(coreApi, appDb)
    }

}

