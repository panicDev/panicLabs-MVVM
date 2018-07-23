package id.panicLabs.wancak.postlist.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import id.panicLabs.core.di.component.BaseSubComponent
import id.panicLabs.core.di.component.ViewModelKey
import id.panicLabs.wancak.postlist.ui.PostListFragment
import id.panicLabs.wancak.postlist.ui.PostListViewModel
import javax.inject.Singleton

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
@Singleton
@Component(modules = [(PostListModule::class)], dependencies = [(BaseSubComponent::class)])
interface PostListComponent {

    fun injectFragment(postListFragment: PostListFragment)

    @Component.Builder
    interface Builder {
        fun baseSubComponent(baseSubComponent: BaseSubComponent): Builder
        fun build(): PostListComponent
    }
}

@Module
abstract class PostListModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(postListViewModel: PostListViewModel): ViewModel

}