package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.FabPosition
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.warehousemanagerapp.R
import com.example.warehousemanagerapp.data.StoreAction
import com.example.warehousemanagerapp.view.loginWarehouse.annotation.Sampled
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph


import androidx.compose.material3.Icon
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import com.example.warehousemanagerapp.data.Receipt
import com.example.warehousemanagerapp.data.Release
import kotlinx.coroutines.delay


@Composable
fun DocumentContentScreen(
    documentViewModel: DocumentViewModel,
    //name: String,
    navController: NavHostController,
    function: () -> Unit
) {
    //val contractorViewModel: ContractorViewModel = viewModel()
    val preData = mutableStateOf(documentViewModel.getDocuments())
    var data by remember { mutableStateOf(emptyList<StoreAction>()) }
    var isLoading by remember { mutableStateOf(true) }
    //val commodities by contractorViewModel.contractor.collectAsState()

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(true) {
        preData.value
        data = documentViewModel.documents() ?: emptyList()
        delay(500)
        isLoading = false
    }

    //WarehouseManagerAppTheme {
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
            SecondaryTextTab(documentViewModel, data, navController) { function() } //ok
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
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentListItem(document: StoreAction) {
    var pw by remember { mutableStateOf(0) }
    var z by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        pw = when(document) {
            is Receipt -> R.drawable.p_24
            else -> R.drawable.w_24
        }
        z = R.drawable.z_24
        Row {
            Icon(ImageVector.vectorResource(id = pw), contentDescription = null)
            Icon(ImageVector.vectorResource(id = z), contentDescription = null)
        }
    }
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Data: ".plus(document.date ?: ""), fontSize = 16.sp)
            Text(text = document.contractor?.contractorName ?: "", fontSize = 16.sp)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(Alignment.End)
    ) {
        IconButton(onClick = {

        }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.save_24),
                contentDescription = null
            )
        }
    }
}

//@Composable
//fun AnimatedExtendedFloatingActionButtonSample(
//    documents: List<StoreAction>,
//    navController: NavHostController,
//    onClick: () -> Unit
//) {
//    val listState = rememberLazyGridState()
//    // The FAB is initially expanded. Once the first visible item is past the first item we
//    // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
//    val expandedFab by remember {
//        derivedStateOf {
//            listState.firstVisibleItemIndex == 0
//        }
//    }
//    Scaffold(
//        //modifier = Modifier.padding(bottom = 48.dp),
//        floatingActionButton = {
////            ExtendedFloatingActionButton(
////                onClick = onClick,
////                expanded = expandedFab,
//////                icon = { Icon(Icons.Filled.Add, "Localized Description") },
//////                text = { Text(text = "Dodaj") },
////            )
//        },
//        isFloatingActionButtonDocked = false,
//        floatingActionButtonPosition = FabPosition.End,
//    ) {
//
//    }
//}

@Composable
fun ClickableItem(
    item: StoreAction,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .padding(start = 2.dp, end = 2.dp, bottom = 4.dp)
        .clip(CircleShape)
        .border(1.dp, Color.Black, CircleShape)
        .size(50.dp)
        .background(MaterialTheme.colorScheme.tertiaryContainer)
        .clickable { onClick() }
    ) {
        DocumentListItem(document = item)
    }
}

//@Preview
@Composable
@Sampled
@OptIn(ExperimentalMaterial3Api::class)
fun SecondaryTextTab(
    viewModel: DocumentViewModel,
    documents: List<StoreAction>,
    navController: NavHostController,
    onClick: () -> Unit
) {
    var docs by remember { mutableStateOf(documents) }
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Wszyscy", "Wydania", "Przyjecia")
    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = {
                        state = index
                        //viewModel.getDocuments()
                        docs = when (index) {
                            1 -> documents.filterIsInstance<Release>()
                            2 -> documents.filterIsInstance<Receipt>()
                            else -> documents
                        }
                    },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        ListOfDocuments(docs, viewModel, navController)
    }
}

@Composable
fun ListOfDocuments(
    documents: List<StoreAction>,
    viewModel: DocumentViewModel,
    navController: NavHostController
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        state = listState,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(documents) { item ->
            ClickableItem(item = item) {
                viewModel.storeAction = item
                navController.navigate(DocumentInfoGraph.DOCUMENT_INFO)
            }
        }
    }
}

object DocumentsGraph {
    const val DOCUMENTS = "documents_graph" ///??????????????????
}