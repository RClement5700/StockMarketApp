package com.clementcorporation.stockmarketapp.di

import android.content.Context
import androidx.room.Room
import com.clementcorporation.stockmarketapp.data.local.StockDatabase
import com.clementcorporation.stockmarketapp.data.remote.api.StockMarketApi
import com.clementcorporation.stockmarketapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockMarketAppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): StockDatabase {
        return Room.databaseBuilder(
            appContext,
            StockDatabase::class.java,
            "stock_database.db"
        ).build()
    }
    @Provides
    @Singleton
    fun providesStockMarketApi(): StockMarketApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
}