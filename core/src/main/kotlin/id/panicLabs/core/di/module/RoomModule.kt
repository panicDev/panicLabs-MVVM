package id.panicLabs.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.panicLabs.core.db.AppDb
import javax.inject.Singleton

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesAppDb(context: Context) = AppDb.create(context, false)

}
