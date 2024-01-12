package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity

import androidx.compose.ui.res.stringResource
import com.example.warehousemanagerapp.R

sealed class BottomBarCommodityScreen(
    val route: String,
    val title: Int,
    val icon: Int
) {
//   object Orders : BottomBarScreen(
//       route = "ORDERS",
//       title = R.string.orders_bottom_bar_label,
//       icon = Icons.Default.Home
//   )

    object Cancel : BottomBarCommodityScreen(
        route = "CANCEL",
        title = R.string.cancel_label,
        icon = R.drawable.documents_24
    )

    object Release : BottomBarCommodityScreen(
        route = "RELEASE",
        title = R.string.release_label,
        icon = R.drawable.commodities_24
    )

    object Add : BottomBarCommodityScreen(
        route = "ADD",
        title = R.string.add_label,
        icon = R.drawable.contractors_24
    )
}