package id.panicLabs.wancak.postlist.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.panicLabs.core.adapter.DiffData
import id.panicLabs.core.retrofit.responses.SectionResponse
import id.panicLabs.wancak.postlist.databinding.ItemViewPostListBinding

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListAdapter constructor(context: Context,
                                  private val viewModel: PostListViewModel) : RecyclerView.Adapter<PostListViewHolder>() {

    internal lateinit var data: DiffData<SectionResponse.Post>


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        data = DiffData(recyclerView, this) { oldData, newData -> oldData.id == newData.id }
    }


    fun setData(data: List<SectionResponse.Post>?) {
        data?.let { this.data.updateData(it) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val itemBindings = ItemViewPostListBinding.inflate(LayoutInflater.from(parent.context))
        return PostListViewHolder(itemBindings)
    }

    override fun getItemCount(): Int {
        println("PostListAdapter.getItemCount : ${data.size()}")
        return data.size()
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.onBindModel(data[position])
    }

    override fun onViewDetachedFromWindow(holder: PostListViewHolder) {
        holder.onClear()
    }

}

//region ViewHolder declaration
class PostListViewHolder(val itemBindings: ItemViewPostListBinding) : RecyclerView.ViewHolder(itemBindings.root) {
    fun onBindModel(post: SectionResponse.Post) {
        itemBindings.viewModel = PostListItemViewModel(post)
        itemBindings.executePendingBindings()
    }

    fun onClear() {
//        GlideApp.with(itemBindings.root).clear(itemBindings.root.postListItemImageView)
    }

}
//endregion