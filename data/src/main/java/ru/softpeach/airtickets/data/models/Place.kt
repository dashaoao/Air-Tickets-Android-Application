package ru.softpeach.airtickets.data.models

import android.graphics.drawable.Drawable

data class Place(
    val id: Int,
    val image: Drawable?,
    val town: String
)
