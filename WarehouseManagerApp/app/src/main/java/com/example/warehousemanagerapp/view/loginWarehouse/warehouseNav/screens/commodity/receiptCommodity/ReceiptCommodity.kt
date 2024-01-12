package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.ActionCommodities
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.view.loginWarehouse.annotation.Sampled
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.CommodityViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor.ContractorViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHost


@Composable
fun ReceiptCommodity(
    commodityViewModel: CommodityViewModel, contractorViewModel: ContractorViewModel,
    navController: NavHostController
) {
    var date by rememberSaveable { mutableStateOf("") }
    var receiptContractor by rememberSaveable { mutableStateOf(Contractor()) }
    var receipt by rememberSaveable { mutableStateOf("") }
    var releaseCommodity by rememberSaveable { mutableStateOf(Commodity()) }
    var commodity by rememberSaveable { mutableStateOf("") }
    var quantity by remember { mutableIntStateOf(0) }
    Text(text = "Przyjecie", fontSize = 36.sp)
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
            .padding(top = 40.dp)
    ) {
        TextField(
            value = date,
            onValueChange = { value -> date = value.filter { it.isDigit() } },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Data"
                )
            },
            trailingIcon = {
                date = DatePickerDialogSample()
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = receipt,
            onValueChange = { receipt = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Dostawca"
                )
            },
            trailingIcon = {
                receiptContractor = MenuWithContractors(contractorViewModel)
                receipt = receiptContractor.contractorName ?: ""
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
//        TextField(
//            value = commodity,
//            onValueChange = { commodity = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = {
//                Text(
//                    text = "Produkt"
//                )
//            },
//            trailingIcon = {
//                releaseCommodity = MenuWithCommodities(commodityViewModel)
//                commodity = releaseCommodity.commoditiesName ?: ""
//                commodityViewModel.commodity = releaseCommodity
//            }
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Box {
//            if (releaseCommodity.counter?.let { it > 0 } == true) {
//                quantity = SliderAdvancedCommodities(commodityViewModel)
//                println("sliderrrr "+ quantity)
//            }
//            else {
//                quantity = 0
//                Toast.makeText(
//                    LocalContext.current, "Brak towarów do wydania", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//        Box {
//            showCommodityAndQuantityField(commodityViewModel, navController)
//        }
        //Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = commodity,
            onValueChange = { commodity = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Produkt"
                )
            },
            trailingIcon = {
                releaseCommodity = MenuWithCommodities(commodityViewModel)
                commodity = releaseCommodity.commoditiesName ?: ""
                commodityViewModel.commodity = releaseCommodity
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box {
                quantity = SliderAdvancedCommodities(commodityViewModel)
                println("sliderrrr " + quantity)
                // runBlocking { commodityViewModel.emitPair(releaseCommodities.toMutableSet()) }
                // runBlocking {
                //commodities?.let {
                //commodityViewModel.releaseCommodities.value?.forEach {
                // if (it.first.commodities != releaseCommodity.commodities)
                //commodityViewModel.emitPair(Pair(releaseCommodity, quantity))
                //runBlocking { commodityViewModel.replacePair(Pair(releaseCommodity, quantity)) }
//                    }

                //}
                //commodityViewModel.releaseCommodities.add(Pair(releaseCommodity, quantity))

        }
        Button(
            onClick = {
                val actualCounter = commodityViewModel.commodity?.counter?.plus(quantity)
                val actionCommodities = ActionCommodities(
                    commodityId = commodityViewModel.commodity?.commodities?.toLong(),
                    quantity = quantity
                )
                val storeAction = StoreAction(
                    actionCommodities, date = date, contractor = receiptContractor, type = "Receipt"
                )
                commodityViewModel.commodity?.counter = actualCounter
                commodityViewModel.putCommodity()
                commodityViewModel.postDocument(storeAction)
                navController.popBackStack()
            },
            modifier = Modifier.align (Alignment.CenterHorizontally),
            enabled = quantity > 0
        ) {
            Text(text = "Przyjmij")
        }


//        Box {
//            AddNewCommodity(commodityViewModel = commodityViewModel, navController)
//        }
        //Spacer(modifier = Modifier.height(8.dp))
//        Button(
//            onClick = {
//                val actualCounter = commodityViewModel.commodity?.counter?.minus(quantity)
//                val actionCommodities = ActionCommodities(
//                    commodityId = commodityViewModel.commodity?.commodities?.toLong(),
//                    quantity = quantity
//                )
//                val storeAction = StoreAction(
//                    // actionCommodities = actionCommodities
//                )
//                commodityViewModel.commodity?.counter = actualCounter
//                commodityViewModel.putCommodity()
//                navController.popBackStack()
//            },
//            modifier = Modifier.align (Alignment.CenterHorizontally),
//            enabled = quantity > 0
//        ) {
//            Text(text = "Wydaj")
//        }
    }
}

@Composable
fun ContentScreen(
    commodityViewModel: CommodityViewModel, contractorViewModel: ContractorViewModel,
    navController: NavHostController
) {

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Sampled
@Composable
fun DatePickerDialogSample(): String {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = remember { mutableStateOf(false) }
    var date by rememberSaveable { mutableStateOf("") }
    // TODO demo how to read the selected date from the state.
    Box {
        IconButton(
            onClick = { openDialog.value = true }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_calendar_month_24),
                contentDescription = null
            )
        }
        if (openDialog.value) {
            val datePickerState = rememberDatePickerState()
            val confirmEnabled = remember {
                derivedStateOf { datePickerState.selectedDateMillis != null }
            }
            DatePickerDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onDismissRequest.
                    openDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            snackScope.launch {
                                val currentDateTime = Date()
                                val currentTimestamp: Long = currentDateTime.time
                                val yyMmDd = datePickerState.selectedDateMillis?.let {
                                    getDate(it, "yyyy-MM-dd")
                                }
                                val hhMmSsMs = getDate(currentTimestamp, "hh:mm:ss.SSS")
                                date = "${yyMmDd}T${hhMmSsMs}"
                            }
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }

    return date
}

@Sampled
@Composable
fun MenuWithContractors(contractorViewModel: ContractorViewModel): Contractor {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val supplierContractors = contractorViewModel.contractors()?.filter { it.supplier!! }
    var release by rememberSaveable { mutableStateOf(Contractor()) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            scrollState = scrollState
        ) {
            repeat(supplierContractors?.size ?: 0) {
                DropdownMenuItem(
                    text = { Text(text = supplierContractors?.get(it)?.contractorName ?: "brak") },
                    onClick = {
                        release = supplierContractors?.get(it) ?: Contractor()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    }
                )
            }
        }
        LaunchedEffect(expanded) {
            if (expanded) {
                // Scroll to show the bottom menu items.
                scrollState.scrollTo(scrollState.maxValue)
            }
        }
    }

    return release
}

@Sampled
@Composable
fun MenuWithCommodities(commodityViewModel: CommodityViewModel): Commodity {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val commodities = commodityViewModel.getCommodities()
    var release by rememberSaveable { mutableStateOf(commodityViewModel.getFirstCommodity() ?: Commodity()) } //commodities?.get(0) ?:

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            scrollState = scrollState
        ) {
            repeat(commodities?.size ?: 0) {
                DropdownMenuItem(
                    text = { Text(text = commodities?.get(it)?.commoditiesName ?: "brak") },
                    onClick = {
                        release = commodities?.get(it) ?: Commodity()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    }
                )
            }
        }
        LaunchedEffect(expanded) {
            if (expanded) {
                // Scroll to show the bottom menu items.
                scrollState.scrollTo(scrollState.maxValue)
            }
        }
    }

    return release
}

private fun getDate(milliSeconds: Long, dateFormat: String?): String? {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = SimpleDateFormat(dateFormat)

    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

@Composable
fun SliderAdvancedCommodities(commodityViewModel: CommodityViewModel): Int {

    var sliderPosition by rememberSaveable {
        mutableFloatStateOf(1f)

    }
    //val a = commodityViewModel.releaseCommodities .forEach { it.second.toFloat() }
    var isLoaded by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(30) }
    LaunchedEffect(key1 = true) {
        isLoaded = true
    }
    if (isLoaded) {
       // counter = commodityViewModel.commodity?.counter ?: 0
        val desc = " z ${commodityViewModel.commodity?.counter}"
                .plus(" (${commodityViewModel.commodity?.unit})")
        Column {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                enabled = counter > 1,
                steps = counter - 1,
                valueRange = 0f..counter.toFloat(),
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = if (counter == 1) counter.toString().plus(desc)
                    else sliderPosition.toInt().toString().plus(desc)
            )
        }
    }

    return if (counter == 1) counter else sliderPosition.toInt()
}

//@Composable
//fun NewCommodityToRelease(
//    commodityViewModel: CommodityViewModel,
//    releaseCommodities: MutableList<Pair<Commodity, Int>>
//): Int {
//   // val list by remember { mutableStateOf(emptySet<Pair<Commodity, Int>>().toMutableSet()) }
//    val listState = rememberLazyGridState()
//    var quantity by remember { mutableStateOf(0) }
//   val releaseCommoditiess = commodityViewModel.releaseCommodities
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(1),
//        state = listState,
//        contentPadding = PaddingValues(4.dp)
//    ) {
//        items(releaseCommodities.size ?: 0) {
//             quantity = showCommodityAndQuantityField(commodityViewModel)
//        }
//    }
//    return quantity
//}

@Composable
fun showCommodityAndQuantityField(commodityViewModel: CommodityViewModel, navController: NavHostController) {
    var releaseCommodity by remember { mutableStateOf(Commodity()) }
    var commodity by remember { mutableStateOf("") }
    var quantity by remember { mutableIntStateOf(0) }
    Column {


    }


}
//
//@Composable
//fun AddNewCommodity(commodityViewModel: CommodityViewModel, navController: NavHostController) {
//    var expanded by remember { mutableStateOf(false) }
//    var quantity by remember { mutableIntStateOf(0) }
//    val commodities = commodityViewModel.getCommodities()
//    var releaseCommodities by remember {
//        mutableStateOf(emptyList<Pair<Commodity, Int>>().toMutableList())
//    }
////    val list by remember {
////        mutableStateOf(emptyList<Pair<Commodity, Int>>().toMutableList())
////    }
//    val newCommodityToRelease = mutableStateOf(
//        NewCommodityToRelease(commodityViewModel, releaseCommodities)
//    )
//    var index = 0
//    //commodityViewModel.commodity?.let { releaseCommodities.add(Pair(it, quantity)) }
//
//    IconButton(
//        onClick = {
//            navController.popBackStack()
//        },
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentWidth(Alignment.Start)
//            .wrapContentHeight(Alignment.Bottom)
//            .background(Color.Transparent)
//    ) { Icon(Icons.Default.MoreVert, contentDescription = "Localized description") }
//
//    IconButton(
//        onClick = {
//
//            //println("dddddddddddd " +releaseCommodities.size)
//           // println("eeeeeeeeeeee $quantity")
//           // releaseCommodities.removeFirst()
//            commodityViewModel. releaseCommodities.value?. forEach { (key, value) -> println("ffffffff $key - $value")
//                //runBlocking { commodityViewModel.emitPair(Pair(key, value)) }
//            }
//                    val actualCounter = commodityViewModel.commodity?.counter?.minus(quantity)
//                    val actionCommodities = ActionCommodities(
//                        commodityId = commodityViewModel.commodity?.commodities?.toLong(),
//                       // quantity = quantity
//                    )
//                    val storeAction = StoreAction(
//                        // actionCommodities = actionCommodities
//                    )
//                   // commodityViewModel.commodity?.counter = actualCounter
//                    //commodityViewModel.putCommodity()
//                    navController.popBackStack()
////                },
////                modifier = Modifier.align (Alignment.CenterHorizontally),
////                enabled = quantity > 0
////            ) {
////                androidx.compose.material3.Text(text = "Wydaj")
////            }
//        },
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentWidth(Alignment.CenterHorizontally)
//            .wrapContentHeight(Alignment.Bottom)
//            .background(Color.Transparent)
//    ) { Icon(Icons.Default.MoreVert, contentDescription = "Localized description") }
//
//    IconButton(
//        onClick = {
//            expanded = true
//
//
//            //quantity =
//
//            val add = Pair(commodityViewModel.getFirstCommodity() ?: Commodity(), quantity)
//            releaseCommodities.add(add)
//            //runBlocking {
////            commodityViewModel.emitPair(add)
////            commodityViewModel.releaseCommodities.value?.forEach {
////                if (it.first.commodities != add.first.commodities)
////                    commodityViewModel.emitPair(add)
////            }
//
//          //  }
//            //newCommodityToRelease.value
//            //newCommodityToRelease.value
//            index++
//        },
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentWidth(Alignment.End)
//            .wrapContentHeight(Alignment.Bottom)
//            .background(Color.Transparent)
//    ) { Icon(Icons.Default.MoreVert, contentDescription = "Localized description") }
//    DropdownMenu(
//        expanded = expanded,
//        properties = PopupProperties(focusable = true),
//        onDismissRequest = { expanded = false }
//    ) { }
//    if (expanded) newCommodityToRelease.value
//}

//@Composable
//private fun BottomBar(
//    navController: NavHostController,
//    commodityViewModel: CommodityViewModel,
//    function: () -> Unit
//) {
//    //ConstraintLayout {
//    val screens = listOf(
//        BottomBarCommodityScreen.Cancel,
//        BottomBarCommodityScreen.Release,
//        BottomBarCommodityScreen.Add,
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//    var expanded by remember { mutableStateOf(false) }
//    val releaseCommodities by remember {
//        mutableStateOf(emptyList<Commodity>().toMutableList())
//    }
//    val newCommodityToRelease = mutableStateOf(
//        NewCommodityToRelease(commodityViewModel, releaseCommodities)
//    )

        //AddNewCommodity(commodityViewModel)

    //val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    //if (bottomBarDestination) {
    //  BottomNavigation(backgroundColor = MaterialTheme.colorScheme.primary) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentWidth(Alignment.CenterHorizontally)
//    ) {

//            //val (button1, button2, button3) = createRefs()
//            Button(
//                //modifier = Modifier.align(Alignment.CenterVertically),
////                modifier = Modifier.constrainAs(button1) {
////                    start.linkTo(parent.start, margin = 16.dp)
////                },
//                onClick = { navController.popBackStack() }) {
//                Text(text = stringResource(id = screens[0].title))
//
//            }
//            Button(
//                //modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
////                modifier = Modifier.constrainAs(button2) {
////                    start.linkTo(button1.end, margin = 4.dp)
////                    linkTo(button1.end, button2.end, startMargin = 30.dp, endMargin = 20.dp, bias = 0F)
////                    width = Dimension.preferredWrapContent
////                    //linkTo(parent.bottom)
////                },
//                onClick = {  }) {
//                Text(text = stringResource(id = screens[1].title))
//            }
//            Button(
//                //modifier = Modifier.wrapContentWidth(Alignment.End),
////                modifier = Modifier.constrainAs(button3) {
////                    start.linkTo(button2.end, margin = 4.dp)
////                    linkTo(button2.end, parent.end, startMargin = 30.dp, endMargin = 20.dp, bias = 0F)
////                    width = Dimension.preferredWrapContent
//                //},
//                onClick = {
//                    function()
//                }
//            ) {
//                Text(text = stringResource(id = screens[2].title))
////                DropdownMenu(
////                    expanded = expanded,
////                    onDismissRequest = { expanded = false }
////                ) { }
//
//            }


//            IconButton(
//                onClick = {
//                    expanded = true
//                    releaseCommodities.add(commodityViewModel.commodity ?: Commodity())
//                },
//                modifier = Modifier
//                    .constrainAs(button3) {
//                    start.linkTo(button2.end, margin = 4.dp)
//                    linkTo(button2.end, parent.end, startMargin = 30.dp, endMargin = 20.dp, bias = 0F)
//                    width = Dimension.preferredWrapContent
//                },
//            ) { Icon(Icons.Default.MoreVert, contentDescription = "Localized description") }
//
//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) { }
//
//            if (expanded) newCommodityToRelease.value

                //screens.forEach { screen ->

//                AddItem(
//                    screen = screen,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
                //   }
                // }
                //}
           // }
       // }
 //   }

//@Composable
//fun RowScope.AddItem(
//    screen: BottomBarCommodityScreen,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//) {
//    BottomNavigationItem(
//        label = {
//            androidx.compose.material3.Text(
//                text = stringResource(id = screen.title),
//                fontSize = 14.sp,
//                color = MaterialTheme.colorScheme.onPrimary
//            )
//        },
//        icon = {
//            Icon(
//                imageVector = ImageVector.vectorResource(id = screen.icon),
//                contentDescription = "Navigation icon",
//                tint = MaterialTheme.colorScheme.onPrimary
//            )
//        },
//        selected = //navController.popBackStack(),
//        currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
//        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
//        onClick = {
//            navController.popBackStack()
////            navController.navigate(screen.route) {
////                popUpTo(navController.graph.findStartDestination().id)
////                launchSingleTop = true
////            }
//        }
//    )
//}


object ReceiptCommodityGraph {
    const val RECEIPT_COMMODITY = "receipt_commodity_graph"
}