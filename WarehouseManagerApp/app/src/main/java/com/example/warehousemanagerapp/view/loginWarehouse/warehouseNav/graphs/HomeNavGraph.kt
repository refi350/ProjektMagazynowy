package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.BottomBarScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.*
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.detail.InformationDetailsScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.detail.OverviewDetailsScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Orders.route
    ) {
        composable(route = BottomBarScreen.Orders.route) {
            HomeContentScreen(
                name = BottomBarScreen.Orders.route,
                {
                    navController.navigate(Graph.DETAIL)
                }
            ) {
                navController.navigate(Graph.DETAIL)
            }
        }
        composable(route = BottomBarScreen.Documents.route) {
            DocumentContentScreen(
                name = BottomBarScreen.Documents.route,
                {
                    navController.navigate(Graph.DETAIL)
                }
            ) {
                navController.navigate(Graph.DETAIL)
            }
        }
        composable(route = BottomBarScreen.Commodity.route) {
            CommodityContentScreen(
                name = BottomBarScreen.Commodity.route,
                {
                    navController.navigate(Graph.DETAIL)
                }
            ) {
                navController.navigate(Graph.DETAIL)
            }
        }

        composable(route = BottomBarScreen.Persons.route) {
            PersonsContentScreen(
                name = BottomBarScreen.Persons.route,
                {
                    navController.navigate(Graph.DETAIL)
                }
            ) {
                navController.navigate(Graph.DETAIL)
            }
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAIL,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            InformationDetailsScreen(
                name = DetailsScreen.Information.route,
                {
                    navController.navigate(DetailsScreen.Overview.route)
                }
            ) {
                navController.navigate(DetailsScreen.Overview.route) //or HOME
            }
        }

        composable(route = DetailsScreen.Overview.route) {
            OverviewDetailsScreen(
                name = DetailsScreen.Overview.route,
                { navController.popBackStack() }
            ) {
                navController.popBackStack()
                //navController.navigate(Graph.HOME) //or Home
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information: DetailsScreen(route = "INFORMATION")
    object Overview: AuthScreen(route = "OVERVIEW")
    //object Details: AuthScreen(route = "items_screen")
    //object ContractorScreen: AuthScreen(route = "contractor_screen")
}
