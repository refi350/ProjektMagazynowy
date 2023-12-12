package com.example.warehousemanagerapp.view.loginWarehouse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.service.User
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LogScreenViewModel : ViewModel() {
    var color: String = ""
  // var color by remember { mutableStateOf("") }

    var user: User? = null
    //var isUser: Boolean = false
//    var serverResponseState by mutableStateOf(false)
//        private set
    private val repository: WarehouseRepository = WarehouseRepository
    var listWarehousesNames: List<String?>? = null

    fun createUser(name: String, password: String): User = User(name, password)

    fun getWarehousesNames() {
        repository.getWarehousesNames { response ->
            listWarehousesNames = response
        }
    }

    fun isOccupiedName(userName: String): Boolean {
        return listWarehousesNames?.contains(userName) ?: true
    }
}