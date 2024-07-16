package ru.softpeach.airtickets.search

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class PlacesItemCallback: ItemCallback<ru.softpeach.airtickets.data.models.Place>() {
    override fun areItemsTheSame(oldItem: ru.softpeach.airtickets.data.models.Place, newItem: ru.softpeach.airtickets.data.models.Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ru.softpeach.airtickets.data.models.Place, newItem: ru.softpeach.airtickets.data.models.Place): Boolean {
        return oldItem == newItem
    }
}
