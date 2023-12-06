package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.service.User
import com.example.warehousemanagerapp.ui.theme.WarehouseManagerAppTheme
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.CommodityViewModel
import kotlinx.coroutines.runBlocking

class WarehouseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarehouseManagerAppTheme {
                //val commodityViewModel: CommodityViewModel = viewModel()
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}