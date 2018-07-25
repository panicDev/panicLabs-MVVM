package id.panicLabs.core.glide

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by paniclabs on 10/17/17.
 */
internal class OkHttpProvider{
    companion object{

        fun createOkHttpClient(): OkHttpClient {
            val  builder= OkHttpClient.Builder()
            val  loggingInterceptor= HttpLoggingInterceptor()
            loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)

            return builder.build()
        }
    }
}