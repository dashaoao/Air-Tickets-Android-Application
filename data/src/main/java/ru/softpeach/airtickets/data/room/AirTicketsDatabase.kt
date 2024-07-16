package ru.softpeach.airtickets.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.softpeach.airtickets.data.room.dao.FlightDAO
import ru.softpeach.airtickets.data.room.dao.OfferDAO
import ru.softpeach.airtickets.data.room.dao.TicketDAO
import ru.softpeach.airtickets.data.room.entities.FlightDBO
import ru.softpeach.airtickets.data.room.entities.OfferDBO
import ru.softpeach.airtickets.data.room.entities.TicketDBO
import ru.softpeach.airtickets.data.room.utils.Converters

class AirTicketsDatabase internal constructor(private val database: AirTicketsRoomDatabase) {
    val offerDao: OfferDAO
        get() = database.offerDao()

    val flightDao: FlightDAO
        get() = database.flightDao()

    val ticketDao: TicketDAO
        get() = database.ticketDao()
}

@Database(entities = [OfferDBO::class, FlightDBO::class, TicketDBO::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class AirTicketsRoomDatabase : RoomDatabase() {

    abstract fun offerDao(): OfferDAO
    abstract fun flightDao(): FlightDAO
    abstract fun ticketDao(): TicketDAO
}

fun AirTicketsDatabase(context: Context): AirTicketsDatabase {
    val newsRoomDatabase = Room.databaseBuilder(
        checkNotNull(context.applicationContext),
        AirTicketsRoomDatabase::class.java,
        "airtickets"
    ).build()
    return AirTicketsDatabase(newsRoomDatabase)
}
