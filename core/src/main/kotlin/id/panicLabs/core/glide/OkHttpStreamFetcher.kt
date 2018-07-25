package id.panicLabs.core.glide

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import com.bumptech.glide.util.Synthetic
import id.panicLabs.core.BuildConfig
import okhttp3.*
import java.io.IOException
import java.io.InputStream

/**
 * Created by paniclabs on 10/17/17.
 */
internal class OkHttpStreamFetcher(var client: Call.Factory, var url: GlideUrl) : DataFetcher<InputStream> {
    var tag = OkHttpStreamFetcher::class.java.simpleName
    @Synthetic
    var stream: InputStream? = null
    @Synthetic var responseBody: ResponseBody? = null
    @Volatile private var call: Call? = null

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        val requestBuilder = Request.Builder().url(url.toStringUrl())
        requestBuilder.addHeader( "Referer", BuildConfig.BASE_IMG_URL)
        val request = requestBuilder.build()
        call = client.newCall(request)
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException) {
                callback.onLoadFailed(e)
            }
            override fun onResponse(call: Call, response: Response) {
                responseBody= response.body()
                if (response.isSuccessful) {
                    val contentLength = responseBody?.contentLength()
                    stream = contentLength?.let { responseBody?.byteStream()?.let { it1 -> ContentLengthInputStream.obtain(it1, it) } }
                    callback.onDataReady(stream)
                } else {
                    callback.onLoadFailed(HttpException(response.message(), response.code()))
                }
            }
        })
    }


    override fun getDataSource(): DataSource {
        return  DataSource.REMOTE
    }


    override fun cleanup() {
        try {
            stream?.close()
        }catch (e: IOException){
            e.printStackTrace()
        }
        responseBody?.close()
    }

    override fun getDataClass(): Class<InputStream> {
        return  InputStream::class.java
    }


    override fun cancel() {
        val localCall=call
        if (localCall!=null){
            localCall.cancel()
        }
    }
}