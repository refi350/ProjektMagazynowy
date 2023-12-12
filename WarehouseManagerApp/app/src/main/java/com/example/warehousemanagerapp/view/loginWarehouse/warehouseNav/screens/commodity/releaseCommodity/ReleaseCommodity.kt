package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity

import android.util.TimeUtils
import android.widget.Toast
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.Commodity
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.view.loginWarehouse.annotation.Sampled
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.CommodityViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor.ContractorViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReleaseCommodity(
    commodityViewModel: CommodityViewModel, contractorViewModel: ContractorViewModel,
    navController: NavController
) {
    var date by rememberSaveable { mutableStateOf("") }
    var releaseContractor by rememberSaveable { mutableStateOf(Contractor()) }
    var release by rememberSaveable { mutableStateOf("") }
    var releaseCommodity by rememberSaveable { mutableStateOf(Commodity()) }
    var commodity by rememberSaveable { mutableStateOf("") }
    var quantity by remember { mutableIntStateOf(0) }

    Text(text = "Wydanie", fontSize = 36.sp)

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
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
            value = release,
            onValueChange = { release = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Odbiorca"
                )
            },
            trailingIcon = {
                releaseContractor = MenuWithContractors(contractorViewModel)
                release = releaseContractor.contractorName ?: ""
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
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
            if (releaseCommodity.counter?.let { it > 0 } == true) {
                quantity = SliderAdvancedCommodities(commodityViewModel)
                println("sliderrrr "+ quantity)
            }
            else {
                quantity = 0
                Toast.makeText(
                    LocalContext.current, "Brak towarów do wydania", Toast.LENGTH_SHORT
                ).show()
            }
        }
        Button(
            onClick = {
//                val storeAction = StoreAction(
//                    date = date, contractor = releaseContractor, commodities =
//                )
                navController.popBackStack()
            },
            modifier = Modifier.align (Alignment.CenterHorizontally),
            enabled = quantity > 0
        ) {
            androidx.compose.material3.Text(text = "Wydaj")
        }
    }
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
    val contractors = contractorViewModel.contractors()
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
            repeat(contractors?.size ?: 0) {
                DropdownMenuItem(
                    text = { Text(text = contractors?.get(it)?.contractorName ?: "brak") },
                    onClick = {
                        release = contractors?.get(it) ?: Contractor()
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
    var release by rememberSaveable { mutableStateOf(commodities?.get(0) ?: Commodity()) }

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
    var sliderPosition by rememberSaveable { mutableFloatStateOf(1f) }
    var isLoaded by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = true) {
        isLoaded = true
    }
    if (isLoaded) {
        counter = commodityViewModel.commodity?.counter ?: 0
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
                text = if (counter == 1) counter.toString().plus(desc)
                    else sliderPosition.toInt().toString().plus(desc)
            )
        }
    }

    return if (counter == 1) counter else sliderPosition.toInt()
}

object ReleaseCommodityGraph {
    const val RELEASE_COMMODITY = "release_commodity_graph"
}