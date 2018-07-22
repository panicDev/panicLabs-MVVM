package id.panicLabs.core.di.component

import dagger.Component
import id.panicLabs.core.CoreApp
import id.panicLabs.core.di.module.*
import javax.inject.Singleton

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (ViewModelModule::class),
    (RetrofitServiceModule::class), (RoomModule::class), (RepositoryModule::class)])
interface AppComponent {

    fun baseSubComponentBuilder(): BaseSubComponent.Builder

    val coreApp: CoreApp
}