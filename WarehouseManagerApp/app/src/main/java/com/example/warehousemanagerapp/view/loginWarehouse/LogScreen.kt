package com.example.warehousemanagerapp.view.loginWarehouse

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

private lateinit var service: WarehouseService
private lateinit var war: Warehouse
@Composable
fun LogScreen(navController: NavController, name: String?, context: Context) {
    var name by remember {
        mutableStateOf("")
    }
    var password by rememberSaveable() { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
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

            //navController.navigate(Screen.DetailScreen.withArgs(name.ifBlank { " " } ))
//            service = WarehouseService()
//            service.createWarehouseApiClient()
//            val user = User("Testowy", "aaa")
//            service.postWarehouseDelivery(user) { response ->
//                response?.let {
//                     //example
//                    war = response
//                    //Text(text = war.name.toString())
//                    Timber.d ("warehouseName")//. e(response.name)
//                }
//            }
            val call = WarehouseApiClient.warehouse.getWarehouses("Testowy", "aaa")
            call.enqueue(object : Callback<Warehouse> {
                override fun onResponse(call: Call<Warehouse>, response: Response<Warehouse>) {
                    val result: Warehouse? = response.body()
                   // onResult(result)
                    //gsonConvert(result)
                    Timber.tag("Fail123")
                    println("Fail1234")
                }

                override fun onFailure(call: Call<Warehouse>, t: Throwable) {
                    Timber.tag("Fail123")
                    println("Fail123")
                    //onResult(null)
                }
            })

            //Intent(this, WarehouseActivity.)
            val intent = Intent(context, WarehouseActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)// + "/{main_screen}")
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.login_button_label))
        }

        Timber.tag("warehouseName").e("asd")
        //Text(text = "hello, $name")
    }
}