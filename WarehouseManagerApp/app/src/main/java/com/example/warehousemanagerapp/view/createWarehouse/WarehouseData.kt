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
import androidx.navigation.NavController
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.view.Visibility
import com.example.warehousemanagerapp.view.VisibilityOff
import com.example.warehousemanagerapp.R

@Composable
fun WarehouseData(navController: NavController) {
    var address by rememberSaveable { mutableStateOf("") }
    var logo by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }
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
            value = address,
            onValueChange = { address = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.address_label)) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = logo,
            onValueChange = { logo = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.logo_label)) },
            singleLine = true,
//            visualTransformation =
//            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            trailingIcon = {
//                IconButton(onClick = { passwordHidden = !passwordHidden }) {
//                    val visibilityIcon =
//                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
//                    // Please provide localized description for accessibility services
//                    val description = if (passwordHidden) "Show password" else "Hide password"
//                    Icon(imageVector = visibilityIcon, contentDescription = description)
//                }
//            }
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
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            trailingIcon = {
//                IconButton(onClick = { passwordHidden = !passwordHidden }) {
//                    val visibilityIcon =
//                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
//                    // Please provide localized description for accessibility services
//                    val description = if (passwordHidden) "Show password" else "Hide password"
//                    Icon(imageVector = visibilityIcon, contentDescription = description)
//                }
//            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Screen.ConfigScreenWarehouseOwner.withArgs(address.ifBlank { " " } ))
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.next_button_label))
        }
        //Text(text = "hello, $name")
    }
}