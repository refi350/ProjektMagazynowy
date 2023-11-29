package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav


import androidx.lifecycle.*
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WarehouseViewModel : ViewModel() {
    private val repository: WarehouseRepository = WarehouseRepository
    private val _warehouseStateFlow = MutableStateFlow<Warehouse?>(null)
    val warehouseStateFlow: StateFlow<Warehouse?> = _warehouseStateFlow.asStateFlow()
    //private val _dataLoaded = MutableStateFlow(false)
    //val dataLoaded: StateFlow<Boolean> = _dataLoaded.asStateFlow()
    private val _commodities = MutableStateFlow<List<Commodity>?>(emptyList())
    val commodities: StateFlow<List<Commodity>?> = _commodities.asStateFlow()
    var user: User = WarehouseRepository.user

//    init {
//        runBlocking {
//            loadData()
//        }
//    }

    fun loadData() {
        _warehouseStateFlow.value = repository.postWarehouseDelivery(user)
    }

    fun setCommodities() {
        _commodities.value = warehouseStateFlow.value?.commodities
    }

    fun getCommodity() {
        warehouseStateFlow.value?.warehouseId?.let {
            repository.getCommodities(it) { response ->
                _commodities.value = response
            }
        }
    }

    fun postCommodity(commodity: Commodity) {
        warehouseStateFlow.value?.warehouseId?.let {
            repository.postCommodity(it, commodity) { response ->
                response?.let {
                    val warehouse = _warehouseStateFlow.value
                    warehouse?.commodities?.toMutableList()?.add(response)
                    _warehouseStateFlow.value = warehouse
                    setCommodities()
                }
            }
        }
    }
}