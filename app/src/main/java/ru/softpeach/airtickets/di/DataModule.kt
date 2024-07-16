package ru.softpeach.airtickets.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.softpeach.airtickets.BuildConfig
import ru.softpeach.airtickets.data.repositories.FlightRepository
import ru.softpeach.airtickets.data.repositories.OfferRepository
import ru.softpeach.airtickets.data.repositories.PlacesRepository
import ru.softpeach.airtickets.data.repositories.SearchRepository
import ru.softpeach.airtickets.data.repositories.TicketRepository
import ru.softpeach.airtickets.data.room.AirTicketsDatabase
import ru.softpeach.airtickets.data.sharedPrefs.LastSearchSharedPreferences
import ru.softpeach.aittickets.api.ApiFactory
import ru.softpeach.aittickets.api.ApiService

val dataModule = module {

    single<ApiService> {
        ApiFactory.create(BuildConfig.BASE_URL)
    }

    single<AirTicketsDatabase> {
        AirTicketsDatabase(
            context = get()
        )
    }

    single<OfferRepository> {
        OfferRepository(
            context = get(),
            service = get(),
            database = get(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }

    single<FlightRepository> {
        FlightRepository(
            context = get(),
            service = get(),
            database = get(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }

    single<TicketRepository> {
        TicketRepository(
            context = get(),
            service = get(),
            database = get(),
            ioDispatcher = get(named("IODispatcher"))
        )
    }

    single<PlacesRepository> {
        PlacesRepository(
            context = get()
        )
    }
    single<LastSearchSharedPreferences> {
        LastSearchSharedPreferences(
            context = get()
        )
    }

    single<SearchRepository> {
        SearchRepository(
            lastSearchSharedPreferences = get()
        )
    }
}
