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
import id.panicLabs.core.CoreApp
import id.panicLabs.core.adapter.LoadingAdapter
import id.panicLabs.core.di.module.MyViewModelFactory
import id.panicLabs.core.utils.gone
import id.panicLabs.wancak.postlist.R
import id.panicLabs.wancak.postlist.databinding.FragmentPostListBinding
import id.panicLabs.wancak.postlist.di.DaggerPostListComponent
import kotlinx.android.synthetic.main.fragment_post_list.*
import org.jetbrains.anko.toast
import javax.inject.Inject

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

    private lateinit var loadingAdapter: LoadingAdapter

    private var pageId: String = ""
    private var page: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding = FragmentPostListBinding.bind(view)
        viewModel = ViewModelProviders.of(this, viewModelProvider).get(PostListViewModel::class.java)
        dataBinding.viewModel = viewModel

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        postListRecyclerView.layoutManager = staggeredGridLayoutManager
        staggeredGridLayoutManager.isItemPrefetchEnabled = true
        postListAdapter = PostListAdapter(requireContext(),viewModel)
        loadingAdapter = LoadingAdapter(postListAdapter, 5)

        postListRecyclerView.adapter = loadingAdapter

        //Observing Success
        viewModel.observeSection().observe(this, Observer {
            pageId = it?.page?.next.orEmpty()
            requireContext().toast("pageId $pageId")
            postListProgress.gone()
            with(postListAdapter){
                setData(it?.posts)
            }


            if (pageId.isNotEmpty()){
                if (page <= 4){
                    viewModel.loadMore("lol", pageId)
                    page.inc()
                }

            }
        })

        loadingAdapter.setLoadMoreListener {
            requireContext().toast("setLoadMoreListener $pageId")
            if (pageId.isNotEmpty()){
                viewModel.loadMore("lol", pageId)
            }

        }


        //Observing Error
        viewModel.observeError().observe(this, Observer {error ->
            postListProgress.gone()
            error?.let {
                Snackbar.make(dataBinding.postListRecyclerView,"Error : $it",Snackbar.LENGTH_SHORT).show()
            }

        })

        viewModel.observeLoadMore().observe(this, Observer {
            println("PostListFragment.observeLoadMore $it")
            with(postListAdapter){
                setData(it?.posts)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchData("lol")
    }

}
