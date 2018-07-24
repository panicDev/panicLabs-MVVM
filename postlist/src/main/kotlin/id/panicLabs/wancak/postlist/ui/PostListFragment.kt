package id.panicLabs.wancak.postlist.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.panicLabs.core.di.module.MyViewModelFactory
import javax.inject.Inject
import id.panicLabs.wancak.postlist.di.DaggerPostListComponent
import id.panicLabs.core.CoreApp
import id.panicLabs.wancak.postlist.databinding.FragmentPostListBinding
import id.panicLabs.wancak.postlist.R
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_post_list.*

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListFragment: Fragment() {

    //region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory

    private lateinit var dataBinding: FragmentPostListBinding //Generated automatically
    private lateinit var viewModel: PostListViewModel

    private lateinit var postListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerPostListComponent.builder()
                .baseSubComponent(CoreApp.instance.provideBaseSubComponent())
                .build()
                .injectFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding = FragmentPostListBinding.bind(view)
        viewModel = ViewModelProviders.of(this, viewModelProvider).get(PostListViewModel::class.java)
        dataBinding.viewModel = viewModel

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        postListRecyclerView.layoutManager = staggeredGridLayoutManager
        staggeredGridLayoutManager.isItemPrefetchEnabled = true
        postListAdapter = PostListAdapter()

        postListRecyclerView.adapter = postListAdapter


//        viewModel.networkEvents.observe(this, Observer { networkEvent ->
//            Log.d("SARASA", networkEvent)
//        })

//        viewModel.repository.getSection("lol")
//                .subscribeBy(
//                        onError = {
//                            println("PostListFragment.onError : ${it.message}")
//                        },
//                        onSuccess = {
//                            println("PostListFragment.onSuccess : ${it.toString()}")
//                        }
//                )
//        viewModel.repository.fetchSection("lol")
//                .observe(this, Observer {
//                    println("PostListFragment.observe : ${it.toString()}")
//                })

        viewModel.fetchSection.observe(this, Observer { post ->
            println("PostListFragment.observe:  ${post?.toString()}")
            post?.let {
                postListAdapter.listPosts = it.posts
            }
        })
    }
}
