package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor

import androidx.annotation.Sampled
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.Alignment
import com.example.warehousemanagerapp.data.Contractor
import com.example.warehousemanagerapp.ui.theme.WarehouseManagerAppTheme




import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PersonsContentScreen(
    contractorViewModel: ContractorViewModel,
    //name: String,
    navController: NavHostController,
    function: () -> Unit
) {
    //val contractorViewModel: ContractorViewModel = viewModel()
    var data by remember { mutableStateOf(emptyList<Contractor>()) }
    var isLoading by remember { mutableStateOf(true) }
    //val commodities by contractorViewModel.contractor.collectAsState()

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(true) {
        data = contractorViewModel.contractors() ?: emptyList()
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
                //val contractor = listOf(Contractor(0, "asdasd", Address(), true, false, "asdasd" ))
                //AnimatedExtendedFloatingActionButtonSample(data, navController) { function() }
                SecondaryTextTabs(contractorViewModel, data, navController) { function() } //ok
            }
        } else {
            // Wyświetl spinner lub jakiś inny wskaźnik ładowania
            // gdy dane są w trakcie pobierania
            // (możesz użyć CircularProgressIndicator lub innego elementu ładowania)
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun CommodityListItem(contractor: Contractor) {
    Row {
        Column {
            Text(text = contractor.contractorName ?: "", fontSize = 32.sp)
            Text(text = contractor.nip ?: "", fontSize = 32.sp)
        }
    }
}

@Composable
fun AnimatedExtendedFloatingActionButtonSample(
    contractors: List<Contractor>,
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
            items(contractors) { item ->
                ClickableCard(item = item) {
                    navController.navigate(Graph.DETAIL)
                }
            }
        }
    }
}

@Composable
fun ClickableCard(
    item: Contractor,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
        backgroundColor = Color.LightGray
    ) {
        CommodityListItem(contractor = item)
    }
}

//@Preview
@Composable
@Sampled
@OptIn(ExperimentalMaterial3Api::class)
fun SecondaryTextTabs(
    viewModel: ContractorViewModel,
    contractors: List<Contractor>,
    navController: NavHostController,
    onClick: () -> Unit
) {
    var peoples by remember { mutableStateOf(contractors) }
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Wszyscy", "Dostawcy", "Odbiorcy")
    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = {
                        state = index
                        viewModel.getContractors()
                        peoples = when (index) {
                            1 -> contractors.filter { contractor -> contractor.supplier == true }
                            2 -> contractors.filter { contractor -> contractor.recipient == true }
                            else -> contractors
                        }
                    },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        AnimatedExtendedFloatingActionButtonSample(
            contractors = peoples,
            navController = navController
        ) {
            onClick()
        }
//        Text(
////            modifier = Modifier.align(Alignment.CenterHorizontally),
////            text = "Secondary tab ${state + 1} selected",
////            style = MaterialTheme.typography.bodyLarge
//        //)
    }
}

object ContractorGraph {
    const val ADD_CONTRACTOR = "add_contractor_graph"
}