package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.warehousemanagerapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
   object Orders : BottomBarScreen(
       route = "ORDERS",
       title = R.string.orders_bottom_bar_label,
       icon = Icons.Default.Home
   )

    object Documents : BottomBarScreen(
        route = "DOCUMENTS",
        title = R.string.documents_bottom_bar_label,
        icon = Icons.Default.Person
    )

    object Commodity : BottomBarScreen(
        route = "COMMODITY",
        title = R.string.commodity_bottom_bar_label,
        icon = Icons.Default.Settings
    )

    object Persons : BottomBarScreen(
        route = "PERSONS",
        title = R.string.persons_bottom_bar_label,
        icon = Icons.Default.Settings
    )
}