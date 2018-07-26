package id.panicLabs.wancak.postlist.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.panicLabs.core.di.module.MyViewModelFactory
import id.panicLabs.core.state.Status
import javax.inject.Inject
import id.panicLabs.wancak.postlist.di.DaggerPostListComponent
import id.panicLabs.core.CoreApp
import id.panicLabs.core.ui.PRecyclerView
import id.panicLabs.core.utils.gone
import id.panicLabs.core.utils.show
import id.panicLabs.wancak.postlist.databinding.FragmentPostListBinding
import id.panicLabs.wancak.postlist.R
import kotlinx.android.synthetic.main.fragment_post_list.*
import org.jetbrains.anko.toast

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

        //Observing Success
        viewModel.observeSection().observe(this, Observer {
            postListProgress.gone()
            with(postListAdapter){
                listPosts = it?.posts.orEmpty()
            }
        })

        //Observing Error
        viewModel.observeError().observe(this, Observer {error ->
            postListProgress.gone()
            error?.let {
                Snackbar.make(dataBinding.postListRecyclerView,"Error : $it",Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchData("lol")
    }

}
