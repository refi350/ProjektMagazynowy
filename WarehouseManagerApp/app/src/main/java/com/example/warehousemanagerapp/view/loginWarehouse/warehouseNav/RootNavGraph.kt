package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.graphs.authNavGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.home.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAIL = "detail_graph"
}