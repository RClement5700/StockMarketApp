package com.clementcorporation.stockmarketapp.domain.models

import com.clementcorporation.stockmarketapp.data.local.StockListItemEntity

data class StockListItem(
    val symbol: String,
    val name: String,
    val exchange: String
)

fun StockListItem.toStockListItemEntity(): StockListItemEntity {
    return StockListItemEntity(
        symbol = symbol,
        name = name,
        exchange = exchange
    )
}
