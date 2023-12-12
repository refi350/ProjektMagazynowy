package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document

import androidx.lifecycle.ViewModel
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class DocumentViewModel: ViewModel() {
    private val repository: WarehouseRepository = WarehouseRepository
    private val _document = MutableStateFlow<List<StoreAction>?>(emptyList())
    val document: StateFlow<List<StoreAction>?> = _document.asStateFlow()
    var storeAction: StoreAction? = null

//    fun postContractor(contractor: Contractor) {
//        repository.warehouseStateFlow.value?.warehouseId?.let {
//            repository.postContractor(it, contractor) { response ->
//                response?.let {
//                    val warehouse = repository.warehouseStateFlow.value
//                    warehouse?.contractors?.toMutableList()?.add(response)
//                    runBlocking {
//                        _contractor.emit(warehouse?.contractors)
//                        repository.setWarehouse(warehouse)
//                    }
//                }
//            }
//        }
//    }

    fun getDocuments() {
        repository.warehouseStateFlow.value?.warehouseId?.let { id ->
            repository.getDocuments(id) { response ->
                response?.let {
                    val warehouse = repository.warehouseStateFlow.value
                    warehouse?.storeActions = it
                    runBlocking {
                        _document.emit(it)
                        repository.setWarehouse(warehouse)
                    }
                }
            }
        }
    }

    fun documents() = repository.warehouseStateFlow.value?.storeActions
}