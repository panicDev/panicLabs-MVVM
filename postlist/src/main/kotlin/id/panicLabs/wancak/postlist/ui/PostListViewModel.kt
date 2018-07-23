package id.panicLabs.wancak.postlist.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import id.panicLabs.core.repository.CoreRepository
import id.panicLabs.core.retrofit.responses.SectionResponse
import javax.inject.Inject

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListViewModel @Inject constructor(var repository: CoreRepository) : ViewModel() {

    val fetchSection: LiveData<List<SectionResponse.Post>> =
            repository.fetchSection("lol")

    var uiEvents = MutableLiveData<String>()
    var networkEvents = repository.networkStatus


    fun onstarst() {
    }
}