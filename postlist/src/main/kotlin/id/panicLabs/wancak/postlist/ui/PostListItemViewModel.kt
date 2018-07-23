package id.panicLabs.wancak.postlist.ui

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.widget.ImageView
import id.panicLabs.core.di.module.GlideApp
import id.panicLabs.core.retrofit.responses.SectionResponse

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
        //TODO https://stackoverflow.com/questions/44845121/constraintlayout-with-databinding
        @JvmStatic @BindingAdapter(value = "app:cover_image", requireAll = true)
        fun bindImageUrl(imageView: ImageView, post: SectionResponse.Post?) {
            post?.let { imgurPost ->
                val glideBuilder = GlideApp.with(imageView.context)
                        .load(imgurPost.img)
                glideBuilder.into(imageView)
            }
        }
    }
}