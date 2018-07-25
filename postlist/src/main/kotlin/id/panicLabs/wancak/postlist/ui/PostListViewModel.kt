package id.panicLabs.wancak.postlist.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.persistence.room.parser.Section
import id.panicLabs.core.repository.CoreRepository
import id.panicLabs.core.retrofit.responses.SectionResponse
import id.panicLabs.core.state.StateData
import javax.inject.Inject

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListViewModel @Inject constructor(var repository: CoreRepository) : ViewModel() {

    fun fetchData(section: String): LiveData<StateData<SectionResponse>> {
        return repository.fetchSection(section)
    }
}