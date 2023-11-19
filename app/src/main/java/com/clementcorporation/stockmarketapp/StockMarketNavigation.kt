package com.clementcorporation.stockmarketapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clementcorporation.stockmarketapp.presentation.stock_details.StockDetailsScreen
import com.clementcorporation.stockmarketapp.presentation.stock_list.StocksListScreen
import com.clementcorporation.stockmarketapp.util.StockMarketScreens

@Composable
fun StockMarketNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = StockMarketScreens.StocksList.name) {

        composable(StockMarketScreens.StocksList.name) {
            StocksListScreen()
        }
        composable(StockMarketScreens.StockDetails.name) {
            StockDetailsScreen()
        }
    }
}