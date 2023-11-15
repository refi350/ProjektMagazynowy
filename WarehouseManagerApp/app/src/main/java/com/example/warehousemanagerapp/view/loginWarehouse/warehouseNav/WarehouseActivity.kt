package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Text
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.R

class WarehouseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootNavigationGraph(navController = rememberNavController())
        }
    }
}