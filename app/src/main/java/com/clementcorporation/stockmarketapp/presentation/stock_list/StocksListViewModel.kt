package com.clementcorporation.stockmarketapp.presentation.stock_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clementcorporation.stockmarketapp.domain.models.StockListItem
import com.clementcorporation.stockmarketapp.domain.repositories.StockMarketRepository
import com.clementcorporation.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocksListViewModel @Inject constructor(
    private val repo: StockMarketRepository
): ViewModel() {
    private var searchJob: Job? = null
    var state by mutableStateOf(StockListItemState())
    private val _stockListScreenState: MutableStateFlow<StocksListScreenEvents> =
        MutableStateFlow(StocksListScreenEvents.OnLoading)
    val stockListScreenState = _stockListScreenState.asStateFlow()
    init {
        getStockList(true, "")
    }
    private fun getStockList(fetchFromRemote: Boolean, query: String) {
        viewModelScope.launch {
            repo.getStockMarketList(fetchFromRemote, query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { stocks ->
                            _stockListScreenState.value =
                                StocksListScreenEvents.OnStocksRetrieved(stocks)
                            state.copy(companies = stocks)
                        }
                    }

                    is Resource.Loading -> _stockListScreenState.value =
                        StocksListScreenEvents.OnLoading

                    else ->
                        result.message?.let {
                            _stockListScreenState.value =
                                StocksListScreenEvents.OnStocksFailedToLoad(it)
                        }
                }
            }
        }
    }

    fun onEvent(event: StocksListScreenEvents) {
        when(event) {
            is StocksListScreenEvents.OnSearchQueryChange -> {
                _stockListScreenState.value =
                    StocksListScreenEvents.OnSearchQueryChange(event.query)
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getStockList(false, event.query)
                }
            }
            is StocksListScreenEvents.OnRefresh -> {
                getStockList(fetchFromRemote = true, "")
            }
            else -> {}
        }
    }
}

data class StockListItemState(
    val companies: List<StockListItem> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
sealed class StocksListScreenEvents {
    data object OnLoading: StocksListScreenEvents()
    class OnStocksRetrieved(val data: List<StockListItem>): StocksListScreenEvents()
    class OnStocksFailedToLoad(val message: String): StocksListScreenEvents()
    data object OnRefresh: StocksListScreenEvents()
    class OnSearchQueryChange(val query: String): StocksListScreenEvents()
}