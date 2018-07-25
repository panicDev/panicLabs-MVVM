package id.panicLabs.core.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.Call
import java.io.InputStream

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
class OkHttpUrlLoader(var client: Call.Factory) : ModelLoader<GlideUrl, InputStream> {
    override fun buildLoadData(model: GlideUrl, width: Int, height: Int, options: Options): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(model, OkHttpStreamFetcher(client,model))
    }

    override fun handles(model: GlideUrl): Boolean {
        return true
    }

    companion object{
        var factory= object : ModelLoaderFactory<GlideUrl, InputStream> {
            @Volatile private var internalClient: Call.Factory? = null
            private var client: Call.Factory? = null
            init {
                client = getInternalClient()

            }
            private fun getInternalClient(): Call.Factory? {
                if (internalClient == null) {
                    synchronized(this) {
                        if (internalClient == null) {
                            internalClient = OkHttpProvider.createOkHttpClient()
                        }
                    }
                }
                return internalClient
            }
            override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> {
                return  OkHttpUrlLoader(client!!)
            }
            override fun teardown() {
            }
        }
    }
}