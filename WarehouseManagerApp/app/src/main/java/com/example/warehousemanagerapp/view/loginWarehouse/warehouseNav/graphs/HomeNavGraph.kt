package com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.graphs

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.Graph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.BottomBarScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.*
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.*
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity.ReceiptCommodity
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity.ReceiptCommodityGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity.ReceiptItemCommodity
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.receiptCommodity.ReceiptItemCommodityGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity.ReleaseCommodity
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity.ReleaseCommodityGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity.ReleaseItemCommodity
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.commodity.releaseCommodity.ReleaseItemCommodityGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.contractor.*
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.detail.InformationDetailsScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.detail.OverviewDetailsScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document.DocumentContentScreen
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document.DocumentInfoGraph
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document.DocumentViewModel
import com.example.warehousemanagerapp.view.loginWarehouse.warehouseNav.screens.document.StoreDocument
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun HomeNavGraph(navController: NavHostController) {
    val commodityViewModel: CommodityViewModel = viewModel()
    val contractorViewModel: ContractorViewModel = viewModel()
    val documentViewModel: DocumentViewModel = viewModel()
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Commodity.route
    ) {
//        composable(route = BottomBarScreen.Orders.route) {
//                HomeContentScreen(
//                    name = BottomBarScreen.Orders.route,
//                    {
//                        navController.navigate(Graph.DETAIL)
//                    }
//                ) {
//                    navController.navigate(Graph.DETAIL)
//                }
//        }
        composable(route = BottomBarScreen.Documents.route) {
            documentViewModel.getDocuments()
            DocumentContentScreen(
                documentViewModel,
                navController,
                //name = BottomBarScreen.Documents.route,
//                {
//                    navController.navigate(Graph.DETAIL)
//                }
            ) {
                navController.navigate(Graph.DETAIL)
            }
        }

        composable(route = DocumentInfoGraph.DOCUMENT_INFO) {
            documentViewModel.getDocuments()
            StoreDocument(documentViewModel, commodityViewModel)
        }

        composable(route = BottomBarScreen.Commodity.route) {
            //commodityViewModel.getCommodity()
            CommodityContentScreen(
                commodityViewModel,
                name = BottomBarScreen.Commodity.route,
                navController = navController
                //{
                    //navController.navigate(Graph.DETAIL)
                //}
            ) {
                navController.navigate(CommodityGraph.ADD_COMMODITY)
            }
        }

        composable(route = CommodityGraph.ADD_COMMODITY) {
           // val warehouseViewModel: WarehouseViewModel = viewModel()
            AddCommodity(commodityViewModel, navController = navController)
        }

        composable(route = ReleaseItemCommodityGraph.RELEASE_ITEM_COMMODITY) {
            ReleaseItemCommodity(commodityViewModel = commodityViewModel, contractorViewModel, navController = navController)
        }

        composable(route = ReleaseCommodityGraph.RELEASE_COMMODITY) {
            //if (commodityViewModel.commodity?.counter?.let { it == 0 } == true)
            ReleaseCommodity(commodityViewModel, contractorViewModel, navController)
        }

        composable(route = ReceiptItemCommodityGraph.RECEIPT_ITEM_COMMODITY) {
            //if (commodityViewModel.commodity?.counter?.let { it == 0 } == true)
            ReceiptItemCommodity(commodityViewModel, contractorViewModel, navController)
        }

        composable(route = ReceiptCommodityGraph.RECEIPT_COMMODITY) {
            //if (commodityViewModel.commodity?.counter?.let { it == 0 } == true)
            ReceiptCommodity(commodityViewModel, contractorViewModel, navController)
        }

        composable(route = BottomBarScreen.Persons.route) {
            PersonsContentScreen(
               contractorViewModel,
                //name = BottomBarScreen.Persons.route,
                navController
                //navController.navigate(Graph.DETAIL)

            ) {
                navController.navigate(ContractorGraph.ADD_CONTRACTOR)
            }
        }

        composable(route = ContractorGraph.ADD_CONTRACTOR) {
            AddContractor(contractorViewModel, navController)
        }

        composable(route = ContractorDetailGraph.CONTRACTOR_INFO) {
            ContractorDetail(documentViewModel, commodityViewModel, contractorViewModel)
        }

        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAIL,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            InformationDetailsScreen(
                name = DetailsScreen.Information.route,
                {
                    navController.navigate(DetailsScreen.Overview.route)
                }
            ) {
                navController.navigate(DetailsScreen.Overview.route) //or HOME
            }
        }

        composable(route = DetailsScreen.Overview.route) {
            OverviewDetailsScreen(
                name = DetailsScreen.Overview.route,
                { navController.popBackStack() }
            ) {
                navController.popBackStack()
                //navController.navigate(Graph.HOME) //or Home
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information: DetailsScreen(route = "INFORMATION")
    object Overview: AuthScreen(route = "OVERVIEW")
    //object Details: AuthScreen(route = "items_screen")
    //object ContractorScreen: AuthScreen(route = "contractor_screen")
}