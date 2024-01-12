package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity

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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.ActionCommodities
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
fun ReceiptItemCommodity(
    commodityViewModel: CommodityViewModel, contractorViewModel: ContractorViewModel,
    navController: NavController
) {
    var date by rememberSaveable { mutableStateOf("") }
    var supplierContractor by rememberSaveable { mutableStateOf(Contractor()) }
    var supplier by rememberSaveable { mutableStateOf("") }
    //var commodity by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableIntStateOf(1) }
    //var showDialog by remember { mutableStateOf(false) }
    //recipient = contractorViewModel.contractorName


    Text(
        text = "Przyjecie ".plus(commodityViewModel.commodity?.commoditiesName),
        fontSize = 36.sp
    )


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
//        if (showDialog) {
//            DatePickerDialogSample()
//        }
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
                    date = DatePickerDialog()
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = supplier,
            onValueChange = { supplier = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Dostawca"
                )
            },
            trailingIcon = {
                supplierContractor = MenuWithScrollStateSample(contractorViewModel)
                supplier = supplierContractor.contractorName ?: ""
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        Box {
           quantity = SliderAdvanced(commodityViewModel)
        }
        Button(
            onClick = {
                val actualCounter = commodityViewModel.commodity?.counter?.plus(quantity)
                val actionCommodities = ActionCommodities(
                    commodityId = commodityViewModel.commodity?.commodities?.toLong(),
                    quantity = quantity
                )
                val storeAction = StoreAction(
                    actionCommodities, date = date, contractor = supplierContractor, type = "Receipt"
                )
                commodityViewModel.commodity?.counter = actualCounter
                commodityViewModel.putCommodity()
                commodityViewModel.postDocument(storeAction)
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = quantity > 0
        ) {
            androidx.compose.material3.Text(text = "Przyjmij")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Sampled
@Composable
fun DatePickerDialog(): String {
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
                                      getDate(it, "yyyy-MM-dd") }
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
fun MenuWithScrollStateSample(contractorViewModel: ContractorViewModel): Contractor {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val supplierContractors = contractorViewModel.contractors()?.filter { it.supplier!! }
    var supplier by rememberSaveable { mutableStateOf(Contractor()) }

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
                        supplier = supplierContractors?.get(it) ?: Contractor()
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

    return supplier
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
fun SliderAdvanced(commodityViewModel: CommodityViewModel): Int {
    var sliderPosition by remember { mutableFloatStateOf(1f) }
    val counter by remember { mutableStateOf(30) }
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
            valueRange = 0f..counter.toFloat()
        )
        Text(
            text = sliderPosition.toInt().toString()
                .plus(" z ${commodityViewModel.commodity?.counter}")
                .plus(" (${commodityViewModel.commodity!!.unit})")
        )
    }

    return sliderPosition.toInt()
}

object ReceiptItemCommodityGraph {
    const val RECEIPT_ITEM_COMMODITY = "receipt_item_commodity_graph"
}