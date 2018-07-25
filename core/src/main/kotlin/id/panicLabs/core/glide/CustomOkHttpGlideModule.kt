package id.panicLabs.core.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream

/**
 * Created by paniclabs on 10/17/17.
 */
@GlideModule
internal class CustomOkHttpGlideModule : LibraryGlideModule(){
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.factory)
    }
}