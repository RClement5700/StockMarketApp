package com.clementcorporation.stockmarketapp.data.remote.api

import com.clementcorporation.stockmarketapp.BuildConfig
import com.clementcorporation.stockmarketapp.data.remote.dtos.StockDetailsDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockMarketApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getStockMarketList(
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): ResponseBody

    @GET("")
    fun getStockDetails(): StockDetailsDto
}