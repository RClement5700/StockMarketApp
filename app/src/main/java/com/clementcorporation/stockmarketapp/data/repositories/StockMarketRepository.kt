package com.clementcorporation.stockmarketapp.data.repositories

import com.clementcorporation.stockmarketapp.domain.models.StockDetailsUiModel
import com.clementcorporation.stockmarketapp.domain.models.StockListItem

interface StockMarketRepository {
    suspend fun getStockMarketList(): List<StockListItem>
    suspend fun getStockDetails(stockId: String): StockDetailsUiModel
}