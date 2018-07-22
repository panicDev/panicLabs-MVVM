package id.panicLabs.core.di.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}
