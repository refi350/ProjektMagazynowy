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
    //Text(text = "Dokumenty")
    var date by remember { mutableStateOf(documentViewModel.storeAction?.date) }
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

        val listState = rememberLazyGridState()
        val commodities = commodityViewModel.getCommodities()
        val actionCommodities = documentViewModel.storeAction?.actionCommodities
        val itemCommoditiesPair = actionCommodities?.map { actionCommodity ->
            Pair(
                (commodities?.find { commodity -> commodity.commodities?.toLong() ==
                    actionCommodity.commodityId } )?.commoditiesName,
                actionCommodity.quantity
            )
        }


        //println("ccccccccccccc $itemCommodity")
        //documentViewModel.storeAction?.actionCommodities?.forEach { println(it.commodityId) }
        //commodities?.forEach { println(it.commodities) }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = listState,
            contentPadding = PaddingValues(4.dp)
        ) {
            items(itemCommoditiesPair ?: emptyList()) { item ->
                Row(
                    modifier = Modifier.width(4.dp)
                ) {
                    Text(text =(itemCommoditiesPair?.indexOf(item)?.plus(1)).toString() + item.first!!)
                    Text(text = item.second.toString())
                }
            }
        }

    }
}

object DocumentInfoGraph {
    const val DOCUMENT_INFO = "documents_info_graph"
}