package com.example.swiftglide.navigation.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ListTeam (
    val id: Int,
    val name: String,
    val amountOfPlayers: Int,
) : Parcelable