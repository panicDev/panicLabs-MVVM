package id.panicLabs.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import id.panicLabs.core.CoreApp
import javax.inject.Singleton

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Module
class AppModule(val app: CoreApp) {
    @Provides
    @Singleton
    internal fun provideApp(): CoreApp = app

    @Provides
    @Singleton
    internal fun provideContext(): Context = app.baseContext

}
