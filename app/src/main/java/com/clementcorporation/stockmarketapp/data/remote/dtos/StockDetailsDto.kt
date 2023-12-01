package com.clementcorporation.stockmarketapp.data.remote.dtos

data class StockDetailsDto(
    val id: String,
    val name: String,
    val industry: String,
    val country: String,
    val description: String,
    val marketSummary: Map<String, String>
)
