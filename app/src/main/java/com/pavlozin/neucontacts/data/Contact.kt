package com.pavlozin.neucontacts.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "contacts")
@Parcelize
data class Contact(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                   val firstName: String,
                   val lastName: String,
                   val email: String,
                   val phone: String,
                   val address: String,
                   val city: String,
                   val zipCode: Int) : Parcelable