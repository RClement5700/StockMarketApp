package com.clementcorporation.stockmarketapp.presentation.stock_details

import androidx.lifecycle.ViewModel
import com.clementcorporation.stockmarketapp.domain.use_cases.GetStockDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StockDetailsViewModel @Inject constructor(
    private val getStockDetailsUseCase: GetStockDetailsUseCase
): ViewModel() {
}