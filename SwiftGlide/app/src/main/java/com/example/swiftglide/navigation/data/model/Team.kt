package com.example.swiftglide.navigation.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    val id: Int,
    val name: String,
    val amountOfPlayers: Int,
) : Parcelable