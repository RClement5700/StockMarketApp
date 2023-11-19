package com.clementcorporation.stockmarketapp.domain.models

data class StockDetailsUiModel(
    val id: String,
    val name: String,
    val industry: String,
    val country: String,
    val description: String,
    val marketSummary: Map<String, String>
)
