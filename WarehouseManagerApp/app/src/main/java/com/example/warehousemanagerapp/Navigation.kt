package com.example.warehousemanagerapp

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

import com.example.warehousemanagerapp.view.StartScreen
import com.example.warehousemanagerapp.view.createWarehouse.ConfigureLayout
import com.example.warehousemanagerapp.view.createWarehouse.WarehouseData
import com.example.warehousemanagerapp.view.createWarehouse.WarehouseOwner
import com.example.warehousemanagerapp.view.createWarehouse.colorPicker.ColorPicker
import com.example.warehousemanagerapp.view.createWarehouse.colorPicker.ColorPickerGraph
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreen
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreenViewModel
//import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.MainScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseActivity



//import androidx.navigation.navArgument
@Composable
fun Navigation(logScreenViewModel: LogScreenViewModel, context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(logScreenViewModel, navController = navController)
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

                entry -> LogScreen(logScreenViewModel, navController, name = entry.arguments?.getString("name"), context)
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
        ) { ConfigureLayout(logScreenViewModel, navController) }
        composable(
            route = Screen.ConfigScreenWarehouseData.route + "/{config_screen_warehouse_data}"
        ) { WarehouseData(logScreenViewModel, navController) }

        composable(route = ColorPickerGraph.ADD_COLOR_PICKER) {
            ColorPicker(logScreenViewModel, navController)
        }

        composable(
            route = Screen.ConfigScreenWarehouseOwner.route + "/{config_screen_warehouse_owner}"
        ) { WarehouseOwner(navController) }

//        composable(
//            route = Screen.MainScreen.route + "/{main_screen}"
//        ) { Screen.MainScreen(navController) }
//        composable(
//            route = Screen.OrderScreen.route + "/{order_screen}"
//        ) { RootNavigationGraph(navController) }
    }
}