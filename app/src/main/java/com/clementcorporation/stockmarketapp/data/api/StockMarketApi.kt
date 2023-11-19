package com.clementcorporation.stockmarketapp.data.api

import com.clementcorporation.stockmarketapp.data.dtos.StockDetailsDto
import com.clementcorporation.stockmarketapp.data.dtos.StockItemDto
import retrofit2.http.GET

interface StockMarketApi {
    @GET("")
    fun getStockMarketList(): List<StockItemDto>

    @GET("")
    fun getStockDetails(): StockDetailsDto
}