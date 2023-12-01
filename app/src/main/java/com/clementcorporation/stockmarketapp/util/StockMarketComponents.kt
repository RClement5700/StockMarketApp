package com.clementcorporation.stockmarketapp.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.clementcorporation.stockmarketapp.domain.models.StockListItem

@Composable
fun StockListItemUi(stockListItem: StockListItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                       //TODO: Navigate to details screen
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stockListItem.name,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "(${stockListItem.symbol})",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = Color.White
            )
        }
        Text(
            modifier = Modifier.weight(1f).padding(8.dp),
            text = stockListItem.exchange,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Light,
            color = Color.White
        )
    }
}