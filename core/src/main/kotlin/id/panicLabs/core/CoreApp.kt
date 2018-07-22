package id.panicLabs.core

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.stetho.Stetho
import id.panicLabs.core.db.AppDb
import id.panicLabs.core.di.component.AppComponent
import id.panicLabs.core.di.component.DaggerAppComponent
import id.panicLabs.core.di.module.AppModule
import id.panicLabs.core.di.module.RoomModule

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
class CoreApp: Application() {

    private lateinit var appComponent: AppComponent

    fun provideBaseSubComponent() = appComponent.baseSubComponentBuilder().build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        instance = this

        AppDb.create(this, false)

        appComponent = DaggerAppComponent.builder()
                .roomModule(RoomModule())
                .appModule(AppModule(this))
                .build()

        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }

    companion object {
        lateinit var instance: CoreApp
            private set
    }

}
