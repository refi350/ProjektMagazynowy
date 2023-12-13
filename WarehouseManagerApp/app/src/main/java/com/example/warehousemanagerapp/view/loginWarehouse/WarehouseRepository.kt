package com.example.warehousemanagerapp.view.loginWarehouse

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.service.RestApi
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.service.WarehouseApiClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.RestrictsSuspension


object WarehouseRepository {
    var warehouse: Warehouse = Warehouse()
    var user: User = User()
    //var isUser: Boolean? = true
    private val TAG = "TodoRepository"

    private var api: RestApi? = null

    //private var warehouse: Warehouse? = null
    private var isUserLiveData: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()
    //private val todoLiveData: MutableLiveData<Todo> = MutableLiveData<Todo>()


    private val _warehouseStateFlow = MutableStateFlow<Warehouse?>(null)
    val warehouseStateFlow: StateFlow<Warehouse?> = _warehouseStateFlow.asStateFlow()

    init {
        api = WarehouseApiClient.warehouse
    }

    fun loadData() = postWarehouseDelivery(user) { response ->
        _warehouseStateFlow.emit(response)
    }

    suspend fun setWarehouse(warehouse: Warehouse?) {
        _warehouseStateFlow.emit(warehouse)
        //println("ssssssssssss ${warehouseStateFlow.value?.contractors?.get(2)}")
    }

//    suspend fun setCommodities(commodities: List<Commodity>, warehouse: Warehouse?) {
//        _warehouseStateFlow.value?.commodities = commodities
//        setWarehouse(warehouse)
//        //println("ssssssssssss ${warehouseStateFlow.value?.contractors?.get(2)}")
//    }

    fun postWarehouseDelivery(user: User, onResult: suspend (Warehouse?) -> Unit) {
        val call = user.name?.let { user.password?.let { it1 -> api?.getWarehouses(it, it1) } }
        call?.enqueue(object : Callback<Warehouse> {
            override fun onResponse(call: Call<Warehouse>, response: Response<Warehouse>) {
                val result: Warehouse? = response.body()
                //_warehouseStateFlow.value = result
                runBlocking { onResult(result) }
                println("Response1234444")
                 println(result)
            }

            override fun onFailure(call: Call<Warehouse>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail12344444")
                println(t.message)
                //onResult(null)
            }
        })
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

    fun getCommodities(id: Int, onResult: suspend(List<Commodity>?) -> Unit) {
        val call = api?.getCommodities(id)
        call?.enqueue(object : Callback<List<Commodity>> {
            override fun onResponse(call: Call<List<Commodity>>, response: Response<List<Commodity>>) {
                val result: List<Commodity>? = response.body()
                runBlocking { onResult(result) }
                println("Fail123445456666")
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

    fun postContractor(id: Int, contractor: Contractor, onResult: (Contractor?) -> Unit) {
        val call = api?.postContractor(id, contractor)
        call?.enqueue(object : Callback<Contractor> {
            override fun onResponse(call: Call<Contractor>, response: Response<Contractor>) {
                val result: Contractor? = response.body()
                onResult(result)
                println("Fail12344545")
                println(result)
            }

            override fun onFailure(call: Call<Contractor>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail1234545")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun getContractors(id: Int, onResult: (List<Contractor>?) -> Unit) {
        val call = api?.getContractors(id)
        call?.enqueue(object : Callback<List<Contractor>> {
            override fun onResponse(call: Call<List<Contractor>>, response: Response<List<Contractor>>) {
                val result: List<Contractor>? = response.body()
                onResult(result)
                println("Fail12344545")
                println(result)
            }

            override fun onFailure(call: Call<List<Contractor>>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail1234545")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun getDocuments(id: Int, onResult: (List<StoreAction>?) -> Unit) {
        val call = api?.getDocuments(id)
        call?.enqueue(object : Callback<List<StoreAction>> {
            override fun onResponse(call: Call<List<StoreAction>>, response: Response<List<StoreAction>>) {
                val result: List<StoreAction>? = response.body()
                onResult(result)
                println("Result88888")
                println(result)
            }

            override fun onFailure(call: Call<List<StoreAction>>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail88888")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun postDocument(id: Int, storeAction: StoreAction, onResult: (StoreAction?) -> Unit) {
        val call = api?.postStoreAction(id, storeAction)
        call?.enqueue(object : Callback<StoreAction> {
            override fun onResponse(call: Call<StoreAction>, response: Response<StoreAction>) {
                val result: StoreAction? = response.body()
                onResult(result)
                println("Result9999999")
                println(result)
            }

            override fun onFailure(call: Call<StoreAction>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail9999999")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun putCommodity(id: Int, commodity: Commodity, onResult: (Commodity?) -> Unit) {
        val call = api?.putCommodity(id, commodity)
        call?.enqueue(object : Callback<Commodity> {
            override fun onResponse(call: Call<Commodity>, response: Response<Commodity>) {
                val result: Commodity? = response.body()
                onResult(result)
                println("Result888884444")
                println(result)
            }

            override fun onFailure(call: Call<Commodity>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail888884444")
                println(t.message)
                //onResult(null)
            }
        })
    }

    fun contractorDelete(id: Int, onResult: (Int) -> Unit) {
        val call = api?.contractorDelete(id)
        call?.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val result: Int = response.code()
                onResult(result)
                println("Result888884444")
                println(result)
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail888884444")
                println(t.message)
                //onResult(null)
            }
        })
    }

   fun commodityDelete(id: Int, onResult: (Int) -> Unit) {
        val call = api?.commodityDelete(id)
        call?.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val result: Int = response.code()
                onResult(result)
                println("Result888884444222")
                println(result)
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                //Timber.tag("Fail123")
                println("Fail888884444222")
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