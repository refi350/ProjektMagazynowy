package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class CommodityViewModel: ViewModel() {
    private val repository: WarehouseRepository = WarehouseRepository
    private val _commodities = MutableStateFlow<List<Commodity>?>(emptyList())
    val commodities: StateFlow<List<Commodity>?> = _commodities.asStateFlow()
    var commodity: Commodity? = null
    var localDateTime: String = ""

//    init {
//        runBlocking {
//            loadData()
//        }
//    }

    suspend fun loadData() = _commodities.emit(repository.warehouseStateFlow.value?.commodities)


    suspend fun getCommodity() {
        val warehouse = repository.warehouseStateFlow.value
        warehouse?.warehouseId?.let {
            repository.getCommodities(it) { response ->
                _commodities.emit(response)
                repository.setWarehouse(warehouse)
            }
        }
    }

    fun postCommodity(commodity: Commodity) {
        repository.warehouseStateFlow.value?.warehouseId?.let {
            repository.postCommodity(it, commodity) { response ->
                response?.let {
                    val warehouse = repository.warehouseStateFlow.value
                    warehouse?.commodities?.toMutableList()?.add(response)
                    runBlocking {
                        _commodities.emit(warehouse?.commodities)
                        repository.setWarehouse(warehouse)
                    }
                }
            }
        }
    }

    fun getCommodities() = repository.warehouseStateFlow.value?.commodities
}