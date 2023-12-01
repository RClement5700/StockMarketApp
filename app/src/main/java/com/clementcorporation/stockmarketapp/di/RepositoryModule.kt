package com.clementcorporation.stockmarketapp.di

import com.clementcorporation.stockmarketapp.data.csv.CSVParser
import com.clementcorporation.stockmarketapp.data.csv.StockListingParser
import com.clementcorporation.stockmarketapp.data.repositories.StockMarketRepositoryImpl
import com.clementcorporation.stockmarketapp.domain.models.StockListItem
import com.clementcorporation.stockmarketapp.domain.repositories.StockMarketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsStockListingsParser(parser: StockListingParser): CSVParser<StockListItem>

    @Binds
    @Singleton
    abstract fun bindsStockRepository(stockMarketRepositoryImpl: StockMarketRepositoryImpl
    ): StockMarketRepository
}