package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.home

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.BottomBarScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.graphs.HomeNavGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity.ReceiptCommodityGraph

import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity.ReleaseCommodityGraph

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = { TopBar(navController = navController) },
        bottomBar = { BottomBar(navController = navController) },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeNavGraph(navController = navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var exitApp by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                val hexColor = WarehouseRepository.warehouseStateFlow.value?.color ?: "#ffffff"
                println("ddddddd $hexColor")
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Color(android.graphics.Color.parseColor(hexColor)),
                            CircleShape
                        )
                        .clip(CircleShape)
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = WarehouseRepository.user.name ?: "name")
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(ReceiptCommodityGraph.RECEIPT_COMMODITY) }) {
                val image = ImageVector.vectorResource(id = R.drawable.receipt_commodity_48)
                Icon(imageVector = image, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = { navController.navigate(ReleaseCommodityGraph.RELEASE_COMMODITY) }
            ) {
                val image = ImageVector.vectorResource(id = R.drawable.release_commodity_48)
                Icon(imageVector = image, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .clickable {
                        expanded = true
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert, contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false; exitApp = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    content = {
                        DropdownMenuItem(
                            onClick = {
                                // Handle the action
                                expanded = false
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Raporty")
                        }
                        DropdownMenuItem(
                            onClick = {
                                // Handle the action
                                expanded = false
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Edycja magazynu")
                        }
                        DropdownMenuItem(
                            onClick = {
                                // Handle the action
                                exitApp = true
                                expanded = false
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Wyloguj")
                        }
                    }
                )
                if (exitApp) HomePage(navController)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        ),
    )
}

@Composable
fun HomePage(navController: NavHostController) {
    val activity = (LocalContext.current as? Activity)
    activity?.finish()
    navController.popBackStack()
//    navController.navigate(BottomBarScreen.Documents.route) {
//        popUpTo(navController.graph.findStartDestination().id)
//        launchSingleTop = true
//    }
    //navController.popBackStack()
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        //BottomBarScreen.Orders,
        BottomBarScreen.Documents,
        BottomBarScreen.Commodity,
        BottomBarScreen.Persons
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primary) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = stringResource(id = screen.title),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "Navigation icon",
                tint = MaterialTheme.colorScheme.onPrimary
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


