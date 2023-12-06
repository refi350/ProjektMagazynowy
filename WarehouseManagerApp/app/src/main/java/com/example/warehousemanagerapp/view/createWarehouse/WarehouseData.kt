package com.example.warehousemanagerapp.view.createWarehouse

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.view.Visibility
import com.example.warehousemanagerapp.view.VisibilityOff
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Address
import com.example.warehousemanagerapp.view.createWarehouse.colorPicker.ColorPickerGraph
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreenViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import kotlin.math.log

@Composable
fun WarehouseData(logScreenViewModel: LogScreenViewModel, navController: NavController) {
    //val logScreenViewModel: LogScreenViewModel = viewModel()
    var streetName by rememberSaveable { mutableStateOf("") }
    var houseNumber by rememberSaveable { mutableStateOf("") }
    var localNumber by rememberSaveable { mutableStateOf("") }
    var place by rememberSaveable { mutableStateOf("") }
    var code by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }
    color = logScreenViewModel.color

    // var passwordHidden by rememberSaveable { mutableStateOf(true) }
    Text(
        text = stringResource(id = R.string.warehouse_data_label),
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
            value = streetName,
            onValueChange = { streetName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.street_name_label)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = houseNumber,
            onValueChange = { houseNumber = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.house_number_label)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = localNumber,
            onValueChange = { value -> localNumber = value.filter { it.isDigit() } },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.local_number_label)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = place,
            onValueChange = { place = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.place_label)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = code,
            onValueChange = { code = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.code_label)) }
        )


        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = color,
            onValueChange = { color = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.color_label)) },
            singleLine = true,
//            visualTransformation =
//            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(
                    onClick = { navController.navigate(ColorPickerGraph.ADD_COLOR_PICKER) }
                ) {
                    val visibilityIcon = ImageVector.vectorResource(
                        id = R.drawable.baseline_palette_24
                    )
                    Icon(imageVector = visibilityIcon, null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            WarehouseRepository.warehouse.apply {
                this.address = Address(
                    streetName = streetName, houseNumber = houseNumber,
                    localNumber = if (localNumber.isNotBlank())
                        localNumber.toInt() else 0,
                    place = place,
                    code = code
                )
                this.color = color
            }
            navController.navigate(Screen.ConfigScreenWarehouseOwner.withArgs(streetName.ifBlank { " " } ))
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.next_button_label))
        }
        //Text(text = "hello, $name")
    }
}