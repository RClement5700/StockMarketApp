package com.clementcorporation.stockmarketapp.domain.repositories

import com.clementcorporation.stockmarketapp.data.api.StockMarketApi
import com.clementcorporation.stockmarketapp.data.repositories.StockMarketRepository
import com.clementcorporation.stockmarketapp.domain.models.StockDetailsUiModel
import com.clementcorporation.stockmarketapp.domain.models.StockListItem
import javax.inject.Inject

class StockMarketRepositoryImpl @Inject constructor(api: StockMarketApi): StockMarketRepository {
    override suspend fun getStockMarketList(): List<StockListItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getStockDetails(stockId: String): StockDetailsUiModel {
        TODO("Not yet implemented")
    }
}