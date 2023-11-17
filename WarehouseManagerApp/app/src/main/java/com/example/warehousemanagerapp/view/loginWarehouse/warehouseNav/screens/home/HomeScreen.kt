package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.BottomBarScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.graphs.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Orders,
        BottomBarScreen.Documents,
        BottomBarScreen.Commodity,
        BottomBarScreen.Persons
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    //val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    //if (bottomBarDestination) {
                BottomNavigation {
                    screens.forEach { screen ->
                        AddItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }
                }
//                NavigationBar(
//
//                ) {
////                var selectedItem by remember { mutableIntStateOf(0) }
////                val items = listOf("Zamówienia", "Dokumenty", "Towary", "Osoby")
//
//                    screens.forEach { item ->
//                        NavigationBarItem(
//                            icon = {
//                                Icon(
//                                    getIconForScreen(screen = item.route),
//                                    contentDescription = item.route
//                                )
//                            },
//                            label = { Text(item.route) },
//                            selected = selectedScreen == item,
//                            onClick = { selectedScreen = currentDestination },
//                            modifier = Modifier.padding(8.dp),
//                            //alwaysShowLabel = false
//                        )
//                    }
//                }
            //},
//            content = {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(text = "Selected screen: $selectedScreen")
//                    //getScreen(selectedScreen = selectedScreen?.route!!, navController)
//                }
//            }
        //) {

       // }
   // }
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Zamówienia" -> Icons.Default.Home
        "Dokumenty" -> Icons.Default.AccountBox
        "Towary" -> Icons.Default.Add
        "Osoby" -> Icons.Default.Notifications
        else -> Icons.Default.Home
    }
}

@Composable
fun getScreen(selectedScreen: String, navController: NavController) {
//    when (selectedScreen) {
//        "Zamówienia" -> navController.navigate(Screen.OrderScreen.route + "/{order_screen}")
//        "Dokumenty" -> Screen.DocumentScreen.route
//        "Towary" -> Screen.ItemsScreen.route
//        "Osoby" -> Screen.ContractorScreen.route
//    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = stringResource(id = screen.title)) },
        icon = {
               Icon(
                   imageVector = screen.icon,
                   contentDescription = "Navigation icon"
               )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                     launchSingleTop = true
            }
        }
    )
}


