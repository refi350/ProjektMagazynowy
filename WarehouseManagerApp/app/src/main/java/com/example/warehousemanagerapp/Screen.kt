package com.example.warehousemanagerapp

import android.net.Uri

sealed class Screen(val route: String) {
    object  StartScreen: Screen("start_screen")
    object  LogScreen: Screen("log_screen")
    object  MainScreen: Screen("main_screen")
    object  ConfigScreenBasic: Screen("config_screen_basic")
    object  ConfigScreenWarehouseData: Screen("config_screen_warehouse_data")
    object  ConfigScreenWarehouseOwner: Screen("config_screen_warehouse_owner")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
