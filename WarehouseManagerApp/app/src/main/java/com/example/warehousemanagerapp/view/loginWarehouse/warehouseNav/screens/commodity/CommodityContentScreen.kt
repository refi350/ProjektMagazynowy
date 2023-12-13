package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph
import androidx.compose.material.FabPosition
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity.ReceiptItemCommodityGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity.ReleaseItemCommodityGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor.ContractorViewModel
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
    //runBlocking { commodityViewModel.getCommodity() }
    //val data by commodityViewModel.commodities.collectAsState() //{ mutableStateOf(emptyList<Commodity>()) }
    //val preData = mutableStateOf(  )
    val data by rememberUpdatedState(commodityViewModel.commodities.collectAsState())// { mutableStateOf(emptyList<Commodity>()) }
    var isLoading by remember { mutableStateOf(true) }
        LaunchedEffect(true) {
            commodityViewModel.getCommodity()
            data.value
            //data = commodityViewModel.getCommodities() ?: emptyList()
            delay(500)
            isLoading = false
        }
       // WarehouseManagerAppTheme {
            // Wyświetl swoje dane po załadowaniu
            if (!isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Tutaj umieść swój kod UI z wykorzystaniem danych z data
                    //val commodities = rememberSaveable { data }
                    AnimatedExtendedFloatingActionButtonSample(
                        data.value ?: emptyList(), navController, commodityViewModel
                    ) { function() }

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
//}

@Composable
fun ClickableCard(
    commodityViewModel: CommodityViewModel,
    item: Commodity,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
//    val clickTime = LocalDensity.current.density * 300
      val context = LocalContext.current
//    val keyboardController = LocalSoftwareKeyboardController.current
    Card(
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp, bottom = 4.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Black, CircleShape)
            .size(70.dp)
            .timedClick(
                timeInMillis = 500,
            ) { passed: Boolean ->
                commodityViewModel.commodity = item
                if (passed) onLongClick()
                else {
                    val counter = item.counter
                    if (counter?.let { it > 0 } == true) onClick()
                    else Toast
                        .makeText(context, "Brak towarów do wydania", Toast.LENGTH_SHORT)
                        .show()
                }
            },
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        CommodityListItem(commodity = item, commodityViewModel)
    }
}

@Composable
fun CommodityListItem(commodity: Commodity, commodityViewModel: CommodityViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    Box (
        modifier = Modifier
            .wrapContentHeight(Alignment.CenterVertically),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = commodity.commoditiesName ?: "",
                fontSize = 16.sp
            )
            Text(
                text = commodity.counter.toString()
                    .plus(" ")
                    .plus(commodity.unit ?: ""),
                fontSize = 16.sp
            )
            Text(
                text = stringResource(id = R.string.bar_code_label)
                    .plus(" ")
                    .plus(commodity.code.toString()),
                fontSize = 16.sp
            )
        }
    }
    Box(
        modifier = Modifier
            .wrapContentWidth(Alignment.End)
            .wrapContentHeight(Alignment.CenterVertically)
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
            onDismissRequest = { expanded = false; showDialog = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            content = {
                DropdownMenuItem(
                    onClick = {
                        // Handle the action
                        expanded = false
                    }
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Edycja towaru")
                }
                DropdownMenuItem(
                    onClick = {
                        // Handle the action
                        showDialog = true
                        expanded = false
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Usuń towar")
                }

            }
        )
        if (showDialog) dialog(commodity, commodityViewModel)
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
    commodityViewModel: CommodityViewModel,
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
            items(commodities) { item ->
                ClickableCard(
                    commodityViewModel,
                    item = item,
                    { navController.navigate(ReleaseItemCommodityGraph.RELEASE_ITEM_COMMODITY) }
                ) {
                    navController.navigate(ReceiptItemCommodityGraph.RECEIPT_ITEM_COMMODITY)
                }
            }
        }
    }
}

@Composable
fun dialog(commodity: Commodity, viewModel: CommodityViewModel) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Usunąć Produkt o nazwie " +
                        "${commodity.commoditiesName?.uppercase() ?: ""}?"
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start)
                            .padding(end = 16.dp),
                        onClick = { openDialog.value = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Anuluj", color = Color.White)
                    }
                    Button(
                        modifier = Modifier.wrapContentWidth(Alignment.End),
                        onClick = {
                            commodity.commodities?.let { viewModel.commodityDelete(it) }
                            openDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Ok", color = Color.White)
                    }
                }
            }
        )
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