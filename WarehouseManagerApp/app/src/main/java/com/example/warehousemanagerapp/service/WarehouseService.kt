package com.example.warehousemanagerapp.service

import android.app.Application
import android.util.TimeUtils
import com.example.warehousemanagerapp.data.Warehouse
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.Calendar

class WarehouseService: Application() {
    private val retrofit = WarehouseApiClient
    private lateinit var serviceClient: RestApi
    private val user = User(name = "Testowy", password = "aaa")

    fun createWarehouseApiClient() {

           serviceClient = retrofit.warehouse //reate(RestApi::class.java)

            //serviceClient.login()


    }

    fun gsonConvert(warehouseJson: Any?) {
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonTo = gsonPretty.toJson(warehouseJson)
        Timber.tag("warehouse123").e(jsonTo)
    }

    private fun computeDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR).toString()
        var month = (calendar.get(Calendar.MONTH) + 1).toString()
        month = if (month.length == 1) "0$month" else month
        val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        return "$year-$month-${day}T${TimeUtils.getTimeZoneDatabaseVersion()}"
    }


}