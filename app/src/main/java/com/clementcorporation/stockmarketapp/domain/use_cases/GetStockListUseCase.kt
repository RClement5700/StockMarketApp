package com.clementcorporation.stockmarketapp.domain.use_cases

import com.clementcorporation.stockmarketapp.data.repositories.StockMarketRepository
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(repo: StockMarketRepository) {
    operator fun invoke() {

    }
}