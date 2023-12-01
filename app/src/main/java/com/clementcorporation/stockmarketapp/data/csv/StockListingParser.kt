package com.clementcorporation.stockmarketapp.data.csv

import com.clementcorporation.stockmarketapp.domain.models.StockListItem
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockListingParser @Inject constructor(): CSVParser<StockListItem> {
    override suspend fun parse(stream: InputStream): List<StockListItem> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1) //first line of a csv file defines the params of each line
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)
                    StockListItem(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null
                    )
                }.also {
                    csvReader.close()
                }
        }
    }
}