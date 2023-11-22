package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeContentScreen(
    name: String,
    onClick: () -> Unit,
    function: () -> Unit
) {
    //val warehouseViewModel: WarehouseViewModel = viewModel()
    //Text(text = warehouseViewModel.getWarehouseLiveData().value?.name ?: "", Modifier.size(50.dp))

    ConstraintLayout {
        val (but, but2) = createRefs()
        Button(onClick = onClick, modifier = Modifier.constrainAs(but) {
            top.linkTo(parent.top, margin = 32.dp)
        }) {
            Text(text = name)
        }

        Button(onClick = function, modifier = Modifier.constrainAs(but2) {
            top.linkTo(but.bottom, margin = 16.dp)
        }) {
            Text(text = name)
        }
    }
}