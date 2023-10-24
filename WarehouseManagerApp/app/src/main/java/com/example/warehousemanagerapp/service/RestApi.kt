package com.example.warehousemanagerapp.service

import com.example.warehousemanagerapp.data.Warehouse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException

interface RestApi {
    @Headers(ACCEPT, CONTENT_TYPE)
    @GET("warehouses/login")
    fun getWarehouses(
        @Query("name") name: String, @Query("password") password: String
    ): Call<Warehouse>

    companion object {
        const val ACCEPT = "Accept:application/json"
        const val CONTENT_TYPE = "Content-Type:application/json"
        const val AUTHORIZATION = "Authorization: Bearer"
        const val BASE_URL = "https://localhost:8080/"
    }
}

class WarehouseApiClient(private val name: String?, private val password: String?) {
    private var authToken: String? = null
    private val okHttpClient = OkHttpClient().newBuilder().apply {
        addInterceptor(Interceptor { chain ->
            var request: Request = chain.request()
            authToken?.let {
                request = request.newBuilder()
                    .header("Authorization", it)
                    .build()
            }
            chain.proceed(request)
        })
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(RestApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val warehouseApi = retrofit.create(RestApi::class.java)

    fun login() {
        val response = warehouseApi.getWarehouses(name!!, password!!).execute()
        if (response.isSuccessful) {
            //if (response.body() == 200)
            val token = response.headers()["Authorization"]
            token.let {
                authToken = token
            }
        } else throw IOException("Response is not successful $response")
    }
}