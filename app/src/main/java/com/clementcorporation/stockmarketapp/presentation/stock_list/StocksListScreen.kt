package com.clementcorporation.stockmarketapp.presentation.stock_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clementcorporation.stockmarketapp.util.StockListItemUi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun StocksListScreen(
    //navigator: DestinationsNavigator
) {
    val viewModel = hiltViewModel<StocksListViewModel>()
    val screenState = viewModel.stockListScreenState.collectAsStateWithLifecycle(
        lifecycleOwner = LocalLifecycleOwner.current,
        minActiveState = Lifecycle.State.STARTED,
        context = Dispatchers.IO
    ).value
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            placeholder = { Text(
                text = "Search...",
                color = Color.White
                ) },
            maxLines = 1,
            singleLine = true,
            value = state.searchQuery,
            onValueChange = { query ->
                viewModel.onEvent(StocksListScreenEvents.OnSearchQueryChange(query))
            }
        )

        SwipeRefresh(
            state = swipeRefreshState, onRefresh = {
            viewModel.onEvent(StocksListScreenEvents.OnRefresh)
        }) {
            when(screenState) {
                is StocksListScreenEvents.OnStocksRetrieved -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        screenState.data.let { stocks ->
                                items(stocks.size) { index ->
                                    StockListItemUi(stockListItem = stocks[index])
                                    if (index < stocks.size) {
                                        Divider(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp))
                                    }
                                }
                            }
                    }
                }
                is StocksListScreenEvents.OnLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = Color.Blue,
                            strokeWidth = 4.dp
                        )
                    }
                }
                is StocksListScreenEvents.OnStocksFailedToLoad -> {
                    val errorMessage = screenState.message
                    Text(text = errorMessage)
                }
                else -> {}
            }
        }
    }
}