package ru.softpeach.airtickets.flight

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.softpeach.airtickets.data.models.Flight

class FlightItemCallback: ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}
