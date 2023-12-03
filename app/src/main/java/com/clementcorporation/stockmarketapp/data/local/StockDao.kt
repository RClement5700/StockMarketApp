package com.clementcorporation.stockmarketapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockListing(stockListItemEntity: List<StockListItemEntity>)
    @Query("DELETE FROM stocklistitementity")
    suspend fun clearStockListings()
    @Query("SELECT * FROM stocklistitementity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol")
    suspend fun searchStockListing(query: String): List<StockListItemEntity>
}