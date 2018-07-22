package id.panicLabs.core.di.component

import android.content.Context
import dagger.Subcomponent
import id.panicLabs.core.repository.CoreRepository

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Subcomponent
interface BaseSubComponent {

    val coreRepository: CoreRepository

    val context: Context

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseSubComponent
    }
}
