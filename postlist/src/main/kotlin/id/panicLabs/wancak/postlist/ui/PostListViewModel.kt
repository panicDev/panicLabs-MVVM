package id.panicLabs.wancak.postlist.ui

import android.arch.lifecycle.ViewModel
import id.panicLabs.core.repository.CoreRepository
import javax.inject.Inject

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListViewModel @Inject constructor(var repository: CoreRepository) : ViewModel() {

    fun fetchData(section: String) = repository.fetchSection(section)

    fun observeSection() = repository.observeSection()

    fun observeError() = repository.observeError()

}