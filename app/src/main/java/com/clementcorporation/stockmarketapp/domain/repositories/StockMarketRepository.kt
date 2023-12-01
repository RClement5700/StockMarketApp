package com.clementcorporation.stockmarketapp.domain.repositories

import com.clementcorporation.stockmarketapp.domain.models.StockDetailsUiModel
import com.clementcorporation.stockmarketapp.domain.models.StockListItem
import com.clementcorporation.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockMarketRepository {
    suspend fun getStockMarketList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<StockListItem>>>
    suspend fun getStockDetails(stockId: String): Flow<Resource<StockDetailsUiModel>>
}