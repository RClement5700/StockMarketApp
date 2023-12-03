package com.clementcorporation.stockmarketapp.data.remote.dtos

import com.clementcorporation.stockmarketapp.data.local.StockListItemEntity
import com.clementcorporation.stockmarketapp.domain.models.StockListItem

data class StockItemDto(
    val symbol: String,
    val name: String,
    val exchange: String,
    val assetType: String,
    val ipoDate: String?,
    val delistingDate: String?,
    val status: String
)
fun StockItemDto.toStockListItem(): StockListItem {
    return StockListItem(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}
fun StockItemDto.toStockListItemEntity(): StockListItemEntity {
    return StockListItemEntity(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}
