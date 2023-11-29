package com.example.warehousemanagerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.warehousemanagerapp.ui.theme.WarehouseManagerAppTheme
import com.example.warehousemanagerapp.view.loginWarehouse.LogScreenViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.NavigationBarItem
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = applicationContext
        setContent {
            val logScreenViewModel: LogScreenViewModel = viewModel()
            // A surface container using the 'background' color from the theme
            Navigation(logScreenViewModel, context)
        }
    }
}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    WarehouseManagerAppTheme {
//        Greeting("Android")
//    }
//}