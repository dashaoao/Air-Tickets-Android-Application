package ru.softpeach.airtickets.search

import androidx.lifecycle.ViewModel
import ru.softpeach.airtickets.data.models.Place
import ru.softpeach.airtickets.data.repositories.PlacesRepository

class PlacesViewModel(
    placesRepository: PlacesRepository
): ViewModel() {
    val places: List<Place> = placesRepository.getPlaces()
}
