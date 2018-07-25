package id.panicLabs.core.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import id.panicLabs.core.glide.MyGlideUrlLoader
import java.io.InputStream

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@GlideModule
class GlideModule: AppGlideModule() {
    private val cacheSize: Long = 10 * 1024 * 1024

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setMemoryCache(LruResourceCache(cacheSize))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(String::class.java, InputStream::class.java,
                MyGlideUrlLoader.factory)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}