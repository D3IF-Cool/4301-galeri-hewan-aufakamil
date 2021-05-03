package org.d3if4082.galerihewan2.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface HewanApiService {
    private val retrofit
        get() = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(Companion.BASE_URL)
            .build()

    interface HewanApiService {
        @GET("listhewan.json")
        suspend fun getHewan(): String
    }
    object HewanApi {
        val service: HewanApiService by lazy {
            retrofit.create(HewanApiService::class.java)
        }
    }

    companion object {
        private const val BASE_URL: String
            get() = "https://dif.indraazimi.com/"
    }
}