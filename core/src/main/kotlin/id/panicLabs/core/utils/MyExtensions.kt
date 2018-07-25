@file:Suppress("NOTHING_TO_INLINE")

package id.panicLabs.core.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import id.panicLabs.core.BuildConfig

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */

inline fun String.imgUrl(): String {
    return if (this == "${BuildConfig.BASE_IMG_URL}${BuildConfig.BASE_IS_UNSAVE}"){
        this.replace("${BuildConfig.BASE_IMG_URL}${BuildConfig.BASE_IS_UNSAVE}", BuildConfig.BASE_IMG_UNSAVE)
    }else {
        this.replace(BuildConfig.BASE_IMG_URL,"")
    }
}

inline fun View.gone(){
    visibility = GONE
}

inline fun View.show(){
    visibility = VISIBLE
}