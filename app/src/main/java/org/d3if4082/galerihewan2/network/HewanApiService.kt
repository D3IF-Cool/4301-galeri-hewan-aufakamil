package org.d3if4082.galerihewan2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4082.galerihewan2.Hewan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface HewanApiService {
    private const val BASE_URL: String

    private val moshi: String


    private val retrofit
        get() = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

    interface HewanApiService {
        @GET("listhewan.json")
        suspend fun getHewan(): List<Hewan>
    }
    object HewanApi {
        val service: HewanApiService by lazy {
            retrofit.create(HewanApiService::class.java)
        }

        enum class ApiStatus { LOADING, SUCCESS, FAILED }

        fun getHewanUrl(nama: String): String {
            return BASE_URL + "hewan/$nama.jpg"
        }

    }
}