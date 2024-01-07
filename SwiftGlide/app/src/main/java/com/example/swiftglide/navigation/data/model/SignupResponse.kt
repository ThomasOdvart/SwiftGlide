package com.example.swiftglide.navigation.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SignupResponse (

    val validated: Boolean,
    val message: String
)