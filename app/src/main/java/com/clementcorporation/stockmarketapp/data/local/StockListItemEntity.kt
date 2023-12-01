package com.clementcorporation.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.clementcorporation.stockmarketapp.domain.models.StockListItem

@Entity
data class StockListItemEntity(
    val symbol: String,
    val name: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null
)

fun StockListItemEntity.toStockListItem(): StockListItem {
    return StockListItem(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}
