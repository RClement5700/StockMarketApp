package com.clementcorporation.stockmarketapp.di

import com.clementcorporation.stockmarketapp.data.api.StockMarketApi
import com.clementcorporation.stockmarketapp.data.repositories.StockMarketRepository
import com.clementcorporation.stockmarketapp.domain.repositories.StockMarketRepositoryImpl
import com.clementcorporation.stockmarketapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockMarketAppModule {

    @Provides
    @Singleton
    fun providesStockMarketRepository(api: StockMarketApi): StockMarketRepository =
        StockMarketRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesStockMarketApi(): StockMarketApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(StockMarketApi::class.java)
}