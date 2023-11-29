package com.example.warehousemanagerapp.view.createWarehouse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.view.Visibility
import com.example.warehousemanagerapp.view.VisibilityOff
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Owner
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreenViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository

@Composable
fun WarehouseOwner(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var owner by rememberSaveable { mutableStateOf("") }
    Text(
        text = stringResource(id = R.string.owner_data_label),
        fontSize = 36.sp
    )
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            //.fillMaxWidth()
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.email_label)) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = owner,
            onValueChange = { owner = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.owner_label)) },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            WarehouseRepository.warehouse.apply {
                this.owner = Owner(email = email, ownerName = owner)
            }
            //println("asdasd " + WarehouseRepository.warehouse)
            WarehouseRepository.postWarehouse()
            navController.navigate(Screen.StartScreen.route)
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.done_button_label))
        }
    }
}