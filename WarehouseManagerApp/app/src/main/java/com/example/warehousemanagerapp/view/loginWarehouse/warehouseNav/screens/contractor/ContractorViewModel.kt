package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ContractorViewModel: ViewModel() {
    private val repository: WarehouseRepository = WarehouseRepository
    private val _contractor = MutableStateFlow<List<Contractor>?>(emptyList())
    val contractor: StateFlow<List<Contractor>?> = _contractor.asStateFlow()
    var cont: Contractor? = null
    var contractorName = ""

    init {
        getContractors()
    }

    fun postContractor(contractor: Contractor) {
        viewModelScope.launch {
            repository.warehouseStateFlow.value?.warehouseId?.let {
                repository.postContractor(it, contractor) { response ->
                    response?.let {
                        val warehouse = repository.warehouseStateFlow.value
                        warehouse?.contractors?.toMutableList()?.add(response)
                        runBlocking {
                            _contractor.emit(warehouse?.contractors)
                            repository.setWarehouse(warehouse)
                        }
                    }
                }
            }
        }
    }

    fun getContractors() {
        viewModelScope.launch {
            repository.warehouseStateFlow.value?.warehouseId?.let { id ->
                repository.getContractors(id) { response ->
                    response?.let {
                        val warehouse = repository.warehouseStateFlow.value
                        warehouse?.contractors = it
                        runBlocking {
                            _contractor.emit(it)
                            repository.setWarehouse(warehouse)
                        }
                    }
                }
            }
        }
    }

    fun contractorDelete(id: Int) {
        viewModelScope.launch {
            repository.warehouseStateFlow.value?.contractors?.let {
                repository.contractorDelete(id) {
                    getContractors()
                }
            }
        }
    }

    fun contractors() = repository.warehouseStateFlow.value?.contractors
}