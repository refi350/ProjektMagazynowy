package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.CommodityViewModel
import java.util.stream.Collectors

@Composable
fun StoreDocument(documentViewModel: DocumentViewModel, commodityViewModel: CommodityViewModel) {
    val date by remember { mutableStateOf(documentViewModel.storeAction?.date) }
    val listState = rememberLazyGridState()
    val commodities = commodityViewModel.getCommodities()
    val actionCommodities = documentViewModel.storeAction?.actionCommodities
    Column(
        modifier = Modifier.fillMaxSize()) {
        Text(text = documentViewModel.storeAction?.type + " nr " +
                documentViewModel.storeAction?.docNumber.toString()
        )
        Row {
            val dateTime = date?.split('T')
            Text(text = "Data: ".uppercase() + (dateTime?.get(0) ?: ""))
            Text(text = " Godzina: ".uppercase() + (dateTime?.get(1) ?: ""))
        }
        Text(text = "Odbiorca: " + documentViewModel.storeAction?.contractor?.contractorName)
        val itemCommoditiesPair = actionCommodities?.map { actionCommodity ->
            Pair(
                commodities?.find {
                        commodity -> commodity.commodities?.toLong() ==
                        actionCommodity.commodityId }?.commoditiesName,
                actionCommodity.quantity
            )
        } ?: List(1) { Pair("Brak produktów", 0) }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = listState,
            contentPadding = PaddingValues(4.dp)
        ) {
            items(itemCommoditiesPair) { pair ->
                Row(
                    modifier = Modifier.width(4.dp)
                ) {
                    Text(text =(
                            itemCommoditiesPair.indexOf(pair).plus(1)).toString()
                            .plus(". ") + pair.first!!)
                    Text(text = " ${pair.second.toString()}")
                }
            }
        }
    }
}

object DocumentInfoGraph {
    const val DOCUMENT_INFO = "documents_info_graph"
}