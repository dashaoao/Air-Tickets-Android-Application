package ru.softpeach.airtickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.softpeach.airtickets.flight.FlightViewModel
import ru.softpeach.airtickets.main.MainViewModel
import ru.softpeach.airtickets.search.PlacesViewModel
import ru.softpeach.airtickets.ticket.TicketViewModel

val appModule = module {

    viewModel {
        MainViewModel(
            offerRepository = get(),
            searchRepository = get()
        )
    }

    viewModel {
        FlightViewModel(
            flightRepository = get()
        )
    }

    viewModel {
        TicketViewModel(
            ticketRepository = get()
        )
    }

    viewModel {
        PlacesViewModel(
            placesRepository = get()
        )
    }
}
