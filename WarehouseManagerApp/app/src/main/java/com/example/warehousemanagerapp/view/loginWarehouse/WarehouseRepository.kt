package com.example.warehousemanagerapp.view.loginWarehouse

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.service.RestApi
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.service.WarehouseApiClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object WarehouseRepository {
    var warehouse: Warehouse = Warehouse()
    var user: User = User()
    //var isUser: Boolean? = true
    private val TAG = "TodoRepository"

    private var api: RestApi? = null

    private var warehouseStateFlow: Warehouse? = null
    private var isUserLiveData: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()
    //private val todoLiveData: MutableLiveData<Todo> = MutableLiveData<Todo>()



    init {
        api = WarehouseApiClient.warehouse
    }

    fun postWarehouseDelivery(user: User): Warehouse? {
        val call = user.name?.let { user.password?.let { it1 -> api?.getWarehouses(it, it1) } }
        call?.enqueue(object : Callback<Warehouse> {
            override fun onResponse(call: Call<Warehouse>, response: Response<Warehouse>) {
                val result: Warehouse? = response.body()
                warehouseStateFlow = result
                //onResult(result)
                println("Fail1234444")
                 println(result)
            }

            override fun onFailure(call: Call<Warehouse>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail12344444")
                println(t.message)
                //onResult(null)
            }
        })
        return warehouseStateFlow
    }

//    fun initIsUser() {
//         isUserLiveData = isWarehouse(user)
//        //return isUser
//    }

    fun isWarehouse(user: User): MutableLiveData<Boolean?> {
        val call = user.name?.let { user.password?.let { it1 -> api?.checkWarehouse(it, it1) } }
        call?.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val result: Boolean? = response.body()
                isUserLiveData.postValue(result)
                //onResult(result)
                println("Done1234")
                println(result)
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail123")
                println(t.message)
                //onResult(null)
            }
        })
        return isUserLiveData
    }

    fun postWarehouse(warehouse: Warehouse, onResult: (Warehouse?) -> Unit) {
        val call = api?.postWarehouse(warehouse)
        call?.enqueue(object : Callback<Warehouse> {
            override fun onResponse(call: Call<Warehouse>, response: Response<Warehouse>) {
                val result: Warehouse? = response.body()
                onResult(result)
                println("Fail1234")
                println(result)
            }

            override fun onFailure(call: Call<Warehouse>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail123")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun postWarehouse() {
        warehouse.let {
            postWarehouse(it) { response ->
                warehouse = response!!
            }
        }
    }

    fun getWarehousesNames(onResult: (List<String?>?) -> Unit) {
        val call = api?.getNames()
        call?.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                val result: List<String?>? = response.body()
                onResult(result)
                println("Fail1234")
                println(result)
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail12333333")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun getCommodities(id: Int, onResult: (List<Commodity>?) -> Unit) {
        val call = api?.getCommodities(id)
        call?.enqueue(object : Callback<List<Commodity>> {
            override fun onResponse(call: Call<List<Commodity>>, response: Response<List<Commodity>>) {
                val result: List<Commodity>? = response.body()
                onResult(result)
                println("Fail12344545")
                println(result)
            }

            override fun onFailure(call: Call<List<Commodity>>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail1234545")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun postCommodity(id: Int, commodity: Commodity, onResult: (Commodity?) -> Unit) {
        val call = api?.postCommodity(id, commodity)
        call?.enqueue(object : Callback<Commodity> {
            override fun onResponse(call: Call<Commodity>, response: Response<Commodity>) {
                val result: Commodity? = response.body()
                onResult(result)
                println("Fail12344545")
                println(result)
            }

            override fun onFailure(call: Call<Commodity>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail1234545")
                println(t.message)
                //onResult(null)
            }
        })
    }

//    companion object Factory {
//        //private val instance: WarehouseRepository = WarehouseRepository()
//        fun getInstance(): WarehouseRepository = WarehouseRepository()
//    }



}