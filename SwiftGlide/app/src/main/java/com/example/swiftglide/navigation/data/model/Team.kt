package com.example.swiftglide.navigation.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Team (
    val id: Int,
    val name: String,
    val playerList: @RawValue List<Player>,
) : Parcelable