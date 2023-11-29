package com.example.warehousemanagerapp.service

import android.net.Uri.Builder
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.data.Warehouse
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

interface RestApi {
    @GET("warehouses/check")
    fun checkWarehouse(
        @Query("name") name: String, @Query("password") password: String
    ): Call<Boolean>

    @GET("warehouses/login")
    fun getWarehouses(
        @Query("name") name: String, @Query("password") password: String
    ): Call<Warehouse>

    @GET("warehouses/names")
    fun getNames(): Call<List<String>>

    @GET("warehouses/{warehouseId}/commodities/all")
    fun getCommodities(@Path("warehouseId") warehouseId: Int): Call<List<Commodity>>

    @GET("warehouses/{warehouseId}/contractors/all")
    fun getContractors(@Path("warehouseId") warehouseId: Int): Call<List<Contractor>>

    @POST("warehouses")
    fun postWarehouse(@Body request: Warehouse): Call<Warehouse>

    @POST("warehouses/{warehouseId}/commodities")
    fun postCommodity(@Path("warehouseId") warehouseId: Int, @Body post: Commodity): Call<Commodity>

    @POST("warehouses/{warehouseId}/contractors")
    fun postContractor(
        @Path("warehouseId") warehouseId: Int, @Body post: Contractor
    ): Call<Contractor>

    companion object {
        const val ACCEPT = "Accept:application/json"
        const val CONTENT_TYPE = "Content-Type:application/json"
        const val AUTHORIZATION = "Authorization: Bearer"
        const val BASE_URL = "https://monika.alwaysdata.net/"
    }
}

object WarehouseApiClient {

//    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//        .readTimeout(100, TimeUnit.SECONDS)
//        .connectTimeout(100, TimeUnit.SECONDS)
//        .build()

            private var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(RestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    val warehouse: RestApi = retrofit.create(RestApi::class.java)


}




//    private var authToken: String? = null
//    private val okHttpClient = OkHttpClient().newBuilder().apply {
//        addInterceptor { chain ->
//            var request: Request = chain.request()
//            authToken?.let {
//                request = request.newBuilder()
//                    .header("Authorization", it)
//                    .build()
//
//            }
//            chain.proceed(request)
//        }
//    }.build()
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(RestApi.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttpClient)
//        .build()
//
//    val warehouseApi: RestApi = retrofit.create(RestApi::class.java)
//
//    fun login() {
//        val response = warehouseApi.getWarehouses(name!!, password!!).execute()
//        if (response.isSuccessful) {
//            //if (response.body() == 200)
//            val token = response.headers()["Authorization"]
//            token.let {
//                authToken = token
//            }
//        } else throw IOException("Response is not successful $response")
//    }
//}