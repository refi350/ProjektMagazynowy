package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CommodityViewModel: ViewModel() {
    private val repository: WarehouseRepository = WarehouseRepository
    private val _commodities = MutableStateFlow<List<Commodity>?>(emptyList())
    val commodities: StateFlow<List<Commodity>?> = _commodities.asStateFlow()
    var commodity: Commodity? = null
    var commoditys: MutableList<Commodity>? = null
    var localDateTime: String = ""

//    private val _releaseCommodities = MutableStateFlow<MutableSet<Pair<Commodity, Int>>?>(mutableSetOf())
//    val releaseCommodities: StateFlow<MutableSet<Pair<Commodity, Int>>?> = _releaseCommodities.asStateFlow()
//
//    private val _releasePair = MutableStateFlow<Map<Commodity, Int>?>(emptyMap())
//    val releasePair: StateFlow<Map<Commodity, Int>?> = _releasePair.asStateFlow()


    init {
        getCommodity()
    }

//    suspend fun loadData() = _commodities.emit(repository.warehouseStateFlow.value?.commodities)
//    suspend fun emitPair(pair: MutableSet<Pair<Commodity, Int>>) {
//        // _releasePair.emit(pair)
//        _releaseCommodities.emit(pair)
//    }
//    suspend fun replacePair(pair: Pair<Commodity, Int>) {
//        // _releasePair.emit(pair)
//        _releaseCommodities.emit(mutableSetOf(pair))
//    }

//    suspend fun emitPair(pair: Pair<Commodity, Int>) {
//         _releasePair.emit(mapOf(pair))
//
//    }

    fun getFirstCommodity() = if (commodity == null) setFirstCommodity() else commodity
    private fun setFirstCommodity(): Commodity? {
        commodity = getCommodities()?.get(0)
        return commodity
    }


    fun getCommodity() {
        viewModelScope.launch {
            val warehouse = repository.warehouseStateFlow.value
            warehouse?.warehouseId?.let {
                repository.getCommodities(it) { response ->
                    _commodities.emit(response)
                    repository.setWarehouse(warehouse)
                }
            }
        }
    }

    fun postCommodity(commodity: Commodity) {
        viewModelScope.launch {
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
    }

    fun postDocument(storeAction: StoreAction) {
        viewModelScope.launch {
            repository.warehouseStateFlow.value?.warehouseId?.let {
                repository.postDocument(it, storeAction) { response ->
                    response?.let {
                        val warehouse = repository.warehouseStateFlow.value
                        warehouse?.storeActions?.toMutableList()?.add(storeAction)
                        runBlocking {
                            _commodities.emit(warehouse?.commodities)
                            repository.setWarehouse(warehouse)
                        }
                    }
                }
            }
        }
    }

    fun putCommodity() {
        val warehouse = repository.warehouseStateFlow.value
            this.commodity?.let { it ->
                it.commodities?.let { id ->
                    repository.putCommodity(id, it) { response ->
                        response?.let {
                            val commodities = _commodities.value?.toMutableList()
                            val indexOfCommodity = commodities?.indexOfFirst { commodity ->
                                commodity.commodities == it.commodities
                            }
                            if (indexOfCommodity != -1)
                                indexOfCommodity?.let { index -> commodities.set(index, response) }
                            runBlocking {
                                _commodities.emit(commodities)
                                repository.setWarehouse(warehouse)
                            }
                        }
                    }
                }
            }
    }

    fun commodityDelete(id: Int) {
        viewModelScope.launch {
            repository.warehouseStateFlow.value?.commodities?.let {
                repository.commodityDelete(id) {
                    getCommodity()
                }
            }
        }
    }


    fun getCommodities() = repository.warehouseStateFlow.value?.commodities
}