package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseViewModel

@Composable
fun AddCommodity(warehouseViewModel: WarehouseViewModel, navController: NavController) {
    var name by rememberSaveable { mutableStateOf("") }
    var code by rememberSaveable { mutableStateOf("") }
    var unit by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    androidx.compose.material3.Text(
        text = stringResource(id = R.string.add_commodity_label),
        fontSize = 36.sp
    )
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.name_label)
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = code,
            onValueChange = { value -> code = value.filter { it.isDigit() } },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.code_commodity_label)
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = unit,
            onValueChange = { unit = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.unit_commodity_label)
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(id = R.string.description_commodity_label)
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val commodity = Commodity(
                    commoditiesName = name, code = code.toInt(),
                    unit = unit, description = description
                )
                warehouseViewModel.postCommodity(commodity)
                navController.popBackStack()
            }, modifier = Modifier.align (Alignment.CenterHorizontally)
        ) {
            androidx.compose.material3.Text(text = stringResource(id = R.string.add_commodity_label))
        }
    }
}