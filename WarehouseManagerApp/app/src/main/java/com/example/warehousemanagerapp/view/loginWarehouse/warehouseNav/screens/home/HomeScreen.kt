package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.BottomBarScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.graphs.HomeNavGraph

//@OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(warehouseViewModel: WarehouseViewModel, navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top App Bar")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            HomeNavGraph(warehouseViewModel, navController = navController)
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

        })



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
   // }
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


