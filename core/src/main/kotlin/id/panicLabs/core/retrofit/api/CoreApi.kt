package id.panicLabs.core.retrofit.api

import id.panicLabs.core.retrofit.responses.SectionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author panicLabs
 * @createdOn 24/11/2017
 */
interface CoreApi {

    @GET("{section}")
    fun getSection(@Path("section") section: String) : Single<SectionResponse>
}