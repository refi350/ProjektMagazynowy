package com.example.warehousemanagerapp

import android.app.Service
import android.content.Context
import android.net.Uri
import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.text.htmlEncode
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

//import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = "pppp"
                }
            )
        ) { entry ->
            LogScreen(navController, name = entry.arguments?.getString("name"))
        }

        composable(
            route = Screen.ConfigScreen.route

        ) {
            ConfigureLayout()
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            //.fillMaxWidth()
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
//        TextField(
//            value = text,
//            onValueChange = {
//                text = it
//        },
//            modifier = Modifier.fillMaxWidth()
//        )
        Button(onClick = {
            navController.navigate(Screen.ConfigScreen.route)
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.create_warehouse_label))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screen.DetailScreen.withArgs(text.ifBlank { " " } ))
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
                Text(text = stringResource(id = R.string.sign_in_warehouse_label))
        }
    }
}

@Composable
fun LogScreen(navController: NavController, name: String?) {
    var text1 by remember {
        mutableStateOf("")
    }
    var text2 by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            //.fillMaxWidth()
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = text1,
            onValueChange = {
                text1 = it
        },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = text2,
            onValueChange = {
                text2 = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate(Screen.DetailScreen.withArgs(text1.ifBlank { " " } ))
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "ok") // stringResource(id = R.string.sign_in_warehouse_label))
        }
        //Text(text = "hello, $name")
    }
}

//Configurator
@Composable
fun ConfigureLayout() {

}