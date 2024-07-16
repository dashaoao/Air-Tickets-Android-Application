package ru.softpeach.airtickets.data.repositories

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.softpeach.airtickets.data.models.EmptyData
import ru.softpeach.airtickets.data.models.NoConnection
import ru.softpeach.airtickets.data.models.ResultState
import ru.softpeach.airtickets.data.models.ServerError
import ru.softpeach.airtickets.data.models.Flight
import ru.softpeach.airtickets.data.models.toFlight
import ru.softpeach.airtickets.data.models.toFlightDBO
import ru.softpeach.airtickets.data.room.AirTicketsDatabase
import ru.softpeach.airtickets.data.utils.ApiErrorHandler
import ru.softpeach.airtickets.data.utils.NetworkUtils
import ru.softpeach.aittickets.api.ApiService

class FlightRepository(
    private val context: Context,
    private val service: ApiService,
    private val database: AirTicketsDatabase,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getFlights(): Flow<ResultState<List<Flight>>> {
        return ApiErrorHandler.handleErrors {
            flow {
                emit(ResultState.Loading())
                if (NetworkUtils.isInternetConnected(context)) {
                    val res = service.getTicketsOffers()
                    if (res.isSuccessful) {
                        val flights = res.body()?.ticketsOffers
                        if (flights != null) {
                            val mappedFlights = flights.map { it.toFlight() }
                            emit(ResultState.Success(mappedFlights))
                            saveLocal(mappedFlights)
                        }
                        else {
                            emit(ResultState.Error(EmptyData, getLocal()))
                        }
                    } else
                        emit(ResultState.Error(ServerError, getLocal()))
                } else {
                    emit(ResultState.Error(NoConnection, getLocal()))
                }
            }
        }
    }

    private suspend fun saveLocal(flights: List<Flight>) {
        withContext(ioDispatcher) {
            database.flightDao.clear()
            database.flightDao.insert(flights.map {
                it.toFlightDBO()
            })
        }
    }

    private suspend fun getLocal(): List<Flight> {
        return database.flightDao.getAll().map {
            it.toFlight()
        }
    }
}
