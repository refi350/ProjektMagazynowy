package com.example.warehousemanagerapp

import android.app.Service
import android.content.Context
import android.net.Uri
import android.provider.Settings.Global.getString
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.core.text.htmlEncode
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.data.Warehouse
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.service.WarehouseService

import com.example.warehousemanagerapp.view.StartScreen
import com.example.warehousemanagerapp.view.createWarehouse.ConfigureLayout
import com.example.warehousemanagerapp.view.createWarehouse.WarehouseData
import com.example.warehousemanagerapp.view.createWarehouse.WarehouseOwner
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreen
import com.example.warehousemanagerapp.view.loginWarehouse.NavigationBarItem


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import timber.log.Timber



//import androidx.navigation.navArgument
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(
            route = Screen.LogScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = "name"
                }
            )
        ) {
                entry -> LogScreen(navController, name = entry.arguments?.getString("name"))
        }

        composable(
            route = Screen.ConfigScreenBasic.route + "/{config_screen_basic}"
//                    arguments = listOf(
//                    navArgument("config_screen_basic") {
//                        type = NavType.StringType
//                        nullable = true
//                        defaultValue = "name"
//                    }
//                    )
        ) { ConfigureLayout(navController) }
        composable(
            route = Screen.ConfigScreenWarehouseData.route + "/{config_screen_warehouse_data}"
        ) { WarehouseData(navController) }

        composable(
            route = Screen.ConfigScreenWarehouseOwner.route + "/{config_screen_warehouse_owner}"
        ) { WarehouseOwner(navController) }

        composable(
            route = Screen.MainScreen.route + "/{main_screen}"
        ) { com.example.warehousemanagerapp.view.loginWarehouse.NavigationBar {
//            NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {}) {
//
//            }
            com.example.warehousemanagerapp.view.loginWarehouse.NavigationBarDefaults
        } }
    }
}