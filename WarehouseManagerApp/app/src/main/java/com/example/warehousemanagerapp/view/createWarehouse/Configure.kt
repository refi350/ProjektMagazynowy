package com.example.warehousemanagerapp.view.createWarehouse

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.view.Visibility
import com.example.warehousemanagerapp.view.VisibilityOff
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreenViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository

@Composable
fun ConfigureLayout(logScreenViewModel: LogScreenViewModel, navController: NavController) {
    //val logScreenViewModel: LogScreenViewModel = viewModel()

    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    var isEmptyNameError by rememberSaveable { mutableStateOf(false) }
    var isOccupiedNameError by rememberSaveable { mutableStateOf(false) }
    val errorPasswordMessage = "Hasła nie mogą być różne"
    val errorEmptyNameMessage = "Nazwa nie może być pusta"
    val errorOccupiedNameMessage = "Nazwa jest już zajęta"
    var name by remember {
        mutableStateOf("")
    }
    var password by rememberSaveable() { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    Text(
        text = stringResource(id = R.string.configure_label),
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
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.name_label)) },
            supportingText = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    text =
                        if (isEmptyNameError) errorEmptyNameMessage
                        else if (isOccupiedNameError) errorOccupiedNameMessage
                        else "",
                    textAlign = TextAlign.End
                )
            },
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.password_label)) },
            singleLine = true,
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    // Please provide localized description for accessibility services
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = passwordRepeat,
            onValueChange = { passwordRepeat = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.password_repeat_label)) },
            supportingText = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    text = if (isPasswordError) errorPasswordMessage else "",
                    textAlign = TextAlign.End
                )
            },
            singleLine = true,
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    // Please provide localized description for accessibility services
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            isEmptyNameError = name.isBlank()
            isOccupiedNameError = logScreenViewModel.isOccupiedName(name)
            if (!isEmptyNameError || !isOccupiedNameError) {
                if (password.isNotBlank() || passwordRepeat.isNotBlank()) {
                    isPasswordError = password != passwordRepeat
                    if (!isPasswordError) {
                        WarehouseRepository.warehouse.apply {
                            this.name = name
                            this.password = password
                        }
                        navController.navigate(Screen.ConfigScreenWarehouseData.withArgs(name.ifBlank { " " }))
                    }
                }
            }
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.next_button_label))
        }
        //Text(text = "hello, $name")
    }
}

//fun failedData(): Modifier {
//    //return Modifier.fillMaxWidth().background(Color.Red, RoundedCornerShape(4.dp) )
//}