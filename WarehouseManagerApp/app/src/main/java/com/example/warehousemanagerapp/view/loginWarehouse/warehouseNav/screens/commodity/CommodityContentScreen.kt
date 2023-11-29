package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.FabPosition
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.Screen
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.ui.theme.WarehouseManagerAppTheme
import com.example.warehousemanagerapp.view.loginWarehouse.WarehouseRepository
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.BottomBarScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.WarehouseViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.CommodityViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommodityContentScreen(
    commodityViewModel: CommodityViewModel,
    name: String,
    navController: NavHostController,
    function: () -> Unit
) {
    runBlocking { commodityViewModel.getCommodity() }
    //val data by commodityViewModel.commodities.collectAsState() //{ mutableStateOf(emptyList<Commodity>()) }
    var data by remember { mutableStateOf(emptyList<Commodity>()) }
    var isLoading by remember { mutableStateOf(true) }
        LaunchedEffect(true) {
            data = commodityViewModel.getCommodities() ?: emptyList()
            isLoading = false
        }
        WarehouseManagerAppTheme {
            // Wyświetl swoje dane po załadowaniu
            if (!isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Tutaj umieść swój kod UI z wykorzystaniem danych z data
                    //val commodities = rememberSaveable { data }
                    AnimatedExtendedFloatingActionButtonSample(data, navController) { function() }

                }
            } else {
                // Wyświetl spinner lub jakiś inny wskaźnik ładowania
                // gdy dane są w trakcie pobierania
                // (możesz użyć CircularProgressIndicator lub innego elementu ładowania)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center)
                    )
                }
            }
        }
}

@Composable
fun ClickableCard(
    item: Commodity,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
//    val clickTime = LocalDensity.current.density * 300
      val context = LocalContext.current
//    val keyboardController = LocalSoftwareKeyboardController.current
    Card(
        modifier = Modifier
            .padding(4.dp)
            .timedClick(
                timeInMillis = 1000,
            ) { passed: Boolean ->
                if (passed) onLongClick()
                else onClick()
            },
        backgroundColor = Color.LightGray
    ) {
        CommodityListItem(commodity = item)
    }
}

@Composable
fun CommodityListItem(commodity: Commodity) {
    Row {
        Column {
            Text(text = commodity.commoditiesName ?: "", fontSize = 32.sp)
            Text(text = commodity.counter.toString(), fontSize = 32.sp)
            Text(text = commodity.unit ?: "", fontSize = 32.sp)
            Text(text = commodity.code.toString(), fontSize = 32.sp)
        }
    }
}

@Composable
fun Modifier.timedClick(
    timeInMillis: Long,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: (Boolean) -> Unit
) = composed {

    var timeOfTouch = -1L
    LaunchedEffect(key1 = timeInMillis, key2 = interactionSource) {
        interactionSource.interactions
            .onEach { interaction: Interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        timeOfTouch = System.currentTimeMillis()
                    }
                    is PressInteraction.Release -> {
                        val currentTime = System.currentTimeMillis()
                        onClick(currentTime - timeOfTouch > timeInMillis)
                    }
                    is PressInteraction.Cancel -> {
                        onClick(false)
                    }
                }

            }
            .launchIn(this)
    }

    Modifier.clickable(
        interactionSource = interactionSource,
        indication = rememberRipple(),
        onClick = {}
    )
}

@Composable
fun AnimatedExtendedFloatingActionButtonSample(
    commodities: List<Commodity>,
    navController: NavHostController,
    onClick: () -> Unit
) {
    val listState = rememberLazyGridState()
    // The FAB is initially expanded. Once the first visible item is past the first item we
    // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
    val expandedFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }
    Scaffold(
        //modifier = Modifier.padding(bottom = 48.dp),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onClick,
                expanded = expandedFab,
                icon = { Icon(Icons.Filled.Add, "Localized Description") },
                text = { Text(text = "Dodaj") },
            )
        },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(commodities) {item ->
                ClickableCard(
                    item = item,
                    { navController.navigate(Graph.DETAIL) }
                ) {
                    navController.navigate(Graph.HOME)
                }
            }
        }
    }
}

object CommodityGraph {
    const val ADD_COMMODITY = "add_commodity_graph"
}



//    @Composable
//    fun CommodityContentScree(
//
//    ) {
//        Text(text = "CommodityScreen", Modifier.size(50.dp))
//        ConstraintLayout {
//
//            val (but, but2) = createRefs()
//
//            Button(onClick = onClick, modifier = Modifier.constrainAs(but) {
//                top.linkTo(parent.top, margin = 32.dp)
//            }) {
//                Text(text = name)
//            }
//
//            Button(onClick = function, modifier = Modifier.constrainAs(but2) {
//                top.linkTo(but.bottom, margin = 16.dp)
//            }) {
//                Text(text = name)
//            }
//        }
//    }