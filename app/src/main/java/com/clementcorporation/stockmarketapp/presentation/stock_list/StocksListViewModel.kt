package com.clementcorporation.stockmarketapp.presentation.stock_list

import androidx.lifecycle.ViewModel
import com.clementcorporation.stockmarketapp.domain.use_cases.GetStockListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StocksListViewModel @Inject constructor(
    private val getStockListUseCase: GetStockListUseCase
): ViewModel() {
}