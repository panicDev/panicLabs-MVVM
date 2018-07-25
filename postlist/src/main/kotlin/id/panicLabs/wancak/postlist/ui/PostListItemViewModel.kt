package id.panicLabs.wancak.postlist.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import id.panicLabs.core.di.module.GlideApp
import id.panicLabs.core.retrofit.responses.SectionResponse
import id.panicLabs.core.utils.imgUrl

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class PostListItemViewModel() : ViewModel(){

    constructor(post: SectionResponse.Post): this(){
        postList.set(post)
    }

    val postList = ObservableField<SectionResponse.Post>()

    companion object {
        @JvmStatic @BindingAdapter(value = "app:cover_image", requireAll = true)
        fun bindImageUrl(imageView: ImageView, post: SectionResponse.Post?) {
            val screenWidth = imageView.context.resources.displayMetrics.widthPixels
            post?.let {

                println("PostListItemViewModel.bindImageUrl ${it.img.imgUrl()}")
                val glideBuilder = GlideApp.with(imageView.context)
                        .load(it.img.imgUrl())
                        .override(screenWidth,Target.SIZE_ORIGINAL)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

                glideBuilder.into(imageView)
            }
        }

        @SuppressLint("SetTextI18n")
        @JvmStatic @BindingAdapter(value = "app:text_vote", requireAll = true)
        fun bindTextVote(textView: TextView, post: SectionResponse.Post?) {
            post?.let {
                textView.text = "${it.votes} funs"
            }
        }
    }
}