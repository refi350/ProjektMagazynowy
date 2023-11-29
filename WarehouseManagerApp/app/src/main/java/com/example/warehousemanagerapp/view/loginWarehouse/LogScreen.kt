package com.example.warehousemanagerapp.view.loginWarehouse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RestrictTo
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
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.warehousemanagerapp.MainActivity
import com.example.warehousemanagerapp.view.Visibility
import com.example.warehousemanagerapp.view.VisibilityOff
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.service.WarehouseService
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.service.WarehouseApiClient
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseActivity
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.warehousemanagerapp.data.Commodity
import kotlinx.coroutines.*

private lateinit var warehouseRepository: WarehouseRepository

@Composable
fun LogScreen(logScreenViewModel: LogScreenViewModel, navController: NavController, name: String?, context: Context) {
    //val logScreenViewModel: LogScreenViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var isOccupiedNameError by rememberSaveable { mutableStateOf(false) }
    var isLoading by remember {
        mutableStateOf(true)
    }


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
            label = { Text(text = stringResource(id = R.string.name_label)) }
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

        Button(onClick = {


            //logScreenViewModel.getWarehousesNames()

            WarehouseRepository.user = logScreenViewModel.createUser(name, password)
            runBlocking {
                WarehouseRepository.loadData()
            }


            //logScreenViewModel.initIsUser()

            //val dataLoaded by logScreenViewModel.dataLoaded.collectAsState()
           // println("ddd "+ logScreenViewModel.isOccupiedName(name))
           // isOccupiedNameError = logScreenViewModel.isOccupiedName(name)
                //if (isOccupiedNameError) {
                    val intent = Intent(context, WarehouseActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    println("act123")
                    context.startActivity(intent)
               // } else Toast.makeText(context, "asd", Toast.LENGTH_SHORT).show()


        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.login_button_label))
        }

//        if (isLoading) {
//            CircularProgressIndicator()
//        } else if (logScreenViewModel.serverResponseState) {
//            LaunchedEffect(logScreenViewModel.serverResponseState) {
//                println("act123")
//                if(logScreenViewModel.isUser) {
//
//                }
//
//
//            }
//        } else Toast.makeText(context, "Brak Magazynu", Toast.LENGTH_SHORT).show()

        Timber.tag("warehouseName").e("asd")
        //Text(text = "hello, $name")
    }
}