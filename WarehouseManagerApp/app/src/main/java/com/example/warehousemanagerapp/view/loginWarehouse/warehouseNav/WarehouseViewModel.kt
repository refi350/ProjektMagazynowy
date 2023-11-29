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

    //private val _dataLoaded = MutableStateFlow(false)
    //val dataLoaded: StateFlow<Boolean> = _dataLoaded.asStateFlow()


//    init {
//        runBlocking {
//            loadData()
//        }
//    }






}