package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import com.example.warehousemanagerapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: Int
) {
//   object Orders : BottomBarScreen(
//       route = "ORDERS",
//       title = R.string.orders_bottom_bar_label,
//       icon = Icons.Default.Home
//   )

    object Documents : BottomBarScreen(
        route = "DOCUMENTS",
        title = R.string.documents_bottom_bar_label,
        icon = R.drawable.documents_24
    )

    object Commodity : BottomBarScreen(
        route = "COMMODITIES",
        title = R.string.commodity_bottom_bar_label,
        icon = R.drawable.commodities_24
    )

    object Persons : BottomBarScreen(
        route = "CONTRACTORS",
        title = R.string.persons_bottom_bar_label,
        icon = R.drawable.contractors_24
    )
}