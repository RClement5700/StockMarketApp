package com.clementcorporation.stockmarketapp.data.repositories

import com.clementcorporation.stockmarketapp.data.csv.CSVParser
import com.clementcorporation.stockmarketapp.data.local.StockDatabase
import com.clementcorporation.stockmarketapp.data.local.toStockListItem
import com.clementcorporation.stockmarketapp.data.remote.api.StockMarketApi
import com.clementcorporation.stockmarketapp.domain.models.StockDetailsUiModel
import com.clementcorporation.stockmarketapp.domain.models.StockListItem
import com.clementcorporation.stockmarketapp.domain.models.toStockListItemEntity
import com.clementcorporation.stockmarketapp.domain.repositories.StockMarketRepository
import com.clementcorporation.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockMarketRepositoryImpl @Inject constructor(
    private val api: StockMarketApi,
    private val db: StockDatabase,
    private val parser: CSVParser<StockListItem>
): StockMarketRepository {
    private val dao = db.dao
    override suspend fun getStockMarketList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<StockListItem>>> = flow {
        emit(Resource.Loading())
        val localListings = dao.searchStockListing(query)
        emit(Resource.Success(localListings.map { entity -> entity.toStockListItem() }))
        val isDbEmpty = localListings.isEmpty() && query.isBlank()
        val loadFromCache = !isDbEmpty && !fetchFromRemote
        if (loadFromCache) return@flow
        val remoteListings = try {
            emit(Resource.Loading())
            val response = api.getStockMarketList()
            parser.parse(response.byteStream())
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Failure())
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Failure())
            null
        }
        remoteListings?.let { listings ->
            dao.clearStockListings()
            dao.insertStockListing(listings.map { item -> item.toStockListItemEntity() })
            emit(Resource.Success(
                dao.searchStockListing("").map { entity -> entity.toStockListItem() }
            ))
        }
    }

    override suspend fun getStockDetails(stockId: String): Flow<Resource<StockDetailsUiModel>> {
        TODO("Not yet implemented")
    }
}