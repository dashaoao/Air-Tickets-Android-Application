package ru.softpeach.airtickets.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.softpeach.airtickets.data.models.ResultState
import ru.softpeach.airtickets.data.models.Ticket
import ru.softpeach.airtickets.data.repositories.TicketRepository

class TicketViewModel(
    private val ticketRepository: TicketRepository
): ViewModel() {

    private val _tickets = MutableStateFlow<ResultState<List<Ticket>>>(ResultState.Loading())
    val tickets: StateFlow<ResultState<List<Ticket>>> = _tickets
    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            ticketRepository.getTickets().collect {
                _tickets.value = it
            }
        }
    }
}
