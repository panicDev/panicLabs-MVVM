package id.panicLabs.wancak.postlist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.panicLabs.core.di.module.GlideApp
import id.panicLabs.core.retrofit.responses.SectionResponse
import id.panicLabs.wancak.postlist.databinding.ItemViewPostListBinding
import kotlinx.android.synthetic.main.item_view_post_list.view.*

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListAdapter() : RecyclerView.Adapter<PostListViewHolder>() {

    //region Variables declaration
    var listPosts : List<SectionResponse.Post> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val itemBindings = ItemViewPostListBinding.inflate(LayoutInflater.from(parent.context))
        return PostListViewHolder(itemBindings)
    }

    override fun getItemCount(): Int {
        return listPosts.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        listPosts.getOrNull(position)?.let { post ->
            holder.onBindModel(post)
        } ?: holder.onClear()
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
        GlideApp.with(itemBindings.root).clear(itemBindings.root.postListItemImageView)
    }

}
//endregion