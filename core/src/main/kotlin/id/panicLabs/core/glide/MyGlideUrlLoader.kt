package id.panicLabs.core.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import java.io.InputStream
import java.util.regex.Pattern

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
internal class MyGlideUrlLoader (concreteLoader: ModelLoader<GlideUrl, InputStream>, modelCache: ModelCache<String, GlideUrl>): BaseGlideUrlLoader<String>(concreteLoader,modelCache) {

    val patern = Pattern.compile("__w-((?:-?\\d+)+)__")

    override fun getUrl(model: String, width: Int, height: Int, options: Options): String {
        val m = patern.matcher(model)
        var bestBucket: Int
        if (m.find()) {
            val found = m.group(1).split("-")
            for (item in found) {
                bestBucket = item.toInt()
                if (bestBucket >= width) break
            }
        }
        return model
    }

    override fun handles(model: String): Boolean = true

    companion object {
        val urlCache = ModelCache<String, GlideUrl>(150)
        val factory = object : ModelLoaderFactory<String, InputStream> {
            override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
                return MyGlideUrlLoader(multiFactory.build(GlideUrl::class.java, InputStream::class.java), urlCache)
            }

            override fun teardown() {
            }
        }
    }
}