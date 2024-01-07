package com.example.swiftglide.navigation.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name="email")
    var email: String,

    @ColumnInfo(name="role")
    var role: String,
) : Parcelable